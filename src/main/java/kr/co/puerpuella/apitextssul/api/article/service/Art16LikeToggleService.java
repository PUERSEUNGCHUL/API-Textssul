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

        boolean isLiked = article.getLikeMemberList().stream().anyMatch(member -> member.getUid().equals(SecurityUtil.getCurrentUserIdEx().orElse(null)));

        if (request.getLikeType() != 0 && request.getLikeType() != 1) {
            throw new ApplicationException(ErrorInfo.LIKE_NO_TYPE);
        }

        // 좋아요취소요청 + 로그인한 회원의 좋아요가 존재할때
        if (request.getLikeType() == 0 && isLiked) {
            article.getLikeMemberList().remove(currentMember);

        // 좋아요요청 + 로그인한 회원의 좋아요가 존재하지 않을 때
        } else if (request.getLikeType() == 1 && !isLiked) {
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
