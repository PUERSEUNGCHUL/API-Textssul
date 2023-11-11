package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art16Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art16Response;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import kr.co.puerpuella.apitextssul.model.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Art16LikeToggleService extends CommonService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art16Request request = (Art16Request) params[0];

        Article article = articleRepository.findOneByArticleId(request.getArticleId());

        Member currentMember = memberRepository.findOneByUid(SecurityUtil.getCurrentUserId());

        long likedMemberCnt = article.getLikeMemberList().stream().filter((m) -> {
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
            article.getLikeMemberList().remove(currentMember);

        // 좋아요요청 + 로그인한 회원의 좋아요가 0건일때
        } else if (request.getLikeType() == 1 && likedMemberCnt == 0) {
            article.getLikeMemberList().add(currentMember);
        }

        Article savedArticle = articleRepository.save(article);

        return Art16Response.builder()
                .articleId(request.getArticleId())
                .likeType(request.getLikeType())
                .likeCnt(savedArticle.getLikeMemberList().size())
                .build();
    }

}
