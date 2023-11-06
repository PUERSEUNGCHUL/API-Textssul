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

        switch (request.getLikeType()) {
            //좋아요
            case 1: {

                article.getLikeMemberList().add(currentMember);
                break;
            }
            //좋아요 취소
            case 0: {
                article.getLikeMemberList().remove(currentMember);
                break;
            }
            //타입을 맘대로 넣은 경우
            default : {

                throw new ApplicationException(ErrorInfo.LIKE_NO_TYPE);
            }
        }

        Article savedArticle = articleRepository.save(article);

        return Art16Response.builder()
                .articleId(savedArticle.getArticleId())
                .likeType(request.getLikeType())
                .likeCnt(savedArticle.getLikeMemberList().size())
                .build();
    }

}
