package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art01Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01SRArticle;
import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseBody;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import kr.co.puerpuella.apitextssul.model.repositories.spec.ArticleSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Art01ArticleListViewService extends CommonService {

    private final ArticleRepository articleRepository;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art01Request request = (Art01Request) params[0];

        System.out.println(ArticleCategory.resolve(request.getCategoryId()));
        System.out.println(request.getCategoryId());
        Specification<Article> spec = Specification.where(ArticleSpecifications.withUserUid(request.getAuthorUid()))
                .and(ArticleSpecifications.withArticleType(request.getArticleTypeId())
                .and(ArticleSpecifications.withCategory(request.getCategoryId())));

        List<Article> articles = articleRepository.findAll(spec);
        //List<Article> articles = articleRepository.findAll();
        Art01Response response = Art01Response.builder()
                .articleList(articles.stream().map((article) -> Art01SRArticle.builder()
                                .articleId(article.getArticleId())
                                .articleTypeId(article.getArticleType().getTypeId())
                                .articleTypeNm(article.getArticleType().getTypeName())
                                .categoryId(article.getArticleCategory().getCategoryId())
                                .categoryNm(article.getArticleCategory().getCategoryName())
                                .articleTitle(article.getArticleTitle())
                                .authorUid(article.getCreateUser().getUid())
                                .authorNick(article.getCreateUser().getNickname())
                                .viewCnt(article.getViewCnt())
                                .likeCnt(article.getLikeMemberList().size())
                                .commentCnt(article.getCommentList().size())
                                .thumbnailImageId(0)
                                .build()
                        ).collect(

                                ArrayList<Art01SRArticle>::new,
                                ArrayList<Art01SRArticle>::add,
                                ArrayList<Art01SRArticle>::addAll)
                ).build();

        return response;

    }
}
