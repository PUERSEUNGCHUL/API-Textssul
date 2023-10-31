package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art04Request;
import kr.co.puerpuella.apitextssul.api.article.dto.request.Art05Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art05Response;
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
public class Art05ArticleDeleteService extends CommonService {

    private final ArticleRepository articleRepository;

    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art05Request request = (Art05Request) params[0];

        Article article = articleRepository.findOneByArticleId(request.getArticleId());

        if (article == null) {
            throw new ApplicationException(ErrorInfo.ARTICLE_NO_RESOURCE);
        }

        //작성자와 로그인한 유저가 같은지 확인
        if (!article.getCreateUser().getUid().equals(SecurityUtil.getCurrentUserId())){

            throw new ApplicationException(ErrorInfo.ARTICLE_NO_MATCH_USER);
        }

        articleRepository.delete(article);

        return Art05Response.builder().build();
    }
}
