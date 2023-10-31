package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art04Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art04Response;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Art04ArticleEditService extends CommonService {

    private final ArticleRepository articleRepository;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art04Request request = (Art04Request) params[0];

        request.setAuthorUid(SecurityUtil.getCurrentUserId());

        Article article = articleRepository.findOneByArticleId(request.getArticleId());

        //작성자와 로그인한 유저가 같은지 확인
        if (!article.getCreateUser().getUid().equals(request.getAuthorUid())){

            throw new ApplicationException(ErrorInfo.ARTICLE_NO_MATCH_USER);
        }

        article.update(request.getArticleTitle(),
                request.getCategoryId(),
                request.getArticleTypeId(),
                request.getArticleContent());

        Article newArticle = articleRepository.save(article);


        return Art04Response.builder().articleId(newArticle.getArticleId()).build();
    }
}
