package kr.co.puerpuella.apitextssul.api.comment.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art16Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art16Response;
import kr.co.puerpuella.apitextssul.api.comment.dto.request.Cmt17Request;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt17Response;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleCommentRepository;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import kr.co.puerpuella.apitextssul.model.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Cmt17LikeToggleService extends CommonService {

    private final ArticleCommentRepository articleCommentRepository;
    private final MemberRepository memberRepository;

    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Cmt17Request request = (Cmt17Request) params[0];

        ArticleComment comment = articleCommentRepository.findById(request.getCommentId()).orElseThrow(() -> new ApplicationException(ErrorInfo.COMMENT_NO_RESOURCE));

        Member currentMember = memberRepository.findOneByUid(SecurityUtil.getCurrentUserId());

        long likedMemberCnt = comment.getLikeMemberList().stream().filter((m) -> {
            try {
                return m.getUid().equals(SecurityUtil.getCurrentUserId());
            } catch (ApplicationException e) {
                return false;
            }
        }).count();

        if (request.getLikeType() != 0 && request.getLikeType() != 1) {
            throw new ApplicationException(ErrorInfo.LIKE_NO_TYPE);
        }

        // 좋아요취소요청 + 로그인한 회원의 좋아요가 1건이상 일때
        if (request.getLikeType() == 0 && likedMemberCnt > 0) {
            comment.getLikeMemberList().remove(currentMember);

        // 좋아요요청 + 로그인한 회원의 좋아요가 0건일때
        } else if (request.getLikeType() == 1 && likedMemberCnt == 0) {
            comment.getLikeMemberList().add(currentMember);
        }

        ArticleComment savedComment = articleCommentRepository.save(comment);

        return Cmt17Response.builder()
                .articleId(request.getArticleId())
                .likeType(request.getLikeType())
                .likeCnt(savedComment.getLikeMemberList().size())
                .build();
    }

}
