package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art01Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01SRArticle;
import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import kr.co.puerpuella.apitextssul.common.enums.OrderDirection;
import kr.co.puerpuella.apitextssul.common.enums.OrderType;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseBody;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import kr.co.puerpuella.apitextssul.model.repositories.spec.ArticleSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 게시글 목록 조회 서비스
 *
 * 게시글 리소스로부터 전달된 파라미터의 검색조건에 적합한 리소스를 반환한다.
 * see also
 */
@Service
@RequiredArgsConstructor
public class Art01ArticleListViewService extends CommonService {

    private final ArticleRepository articleRepository;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art01Request request = (Art01Request) params[0];

        // 목록 페이지 조건 설정
        Pageable pageable = PageRequest.of(
                request.getPage() == null ? 0 : request.getPage(),
                request.getLimit() == null ? 20 : request.getLimit(),
                Sort.by(
                        request.getOrderDirection().getDirection(),
                        request.getOrderType().getSortProperty())
                );

        // 게시글 검색 조건 설정
        Specification<Article> spec = Specification.where(ArticleSpecifications.withUserUid(request.getAuthorUid()))
                .and(ArticleSpecifications.withArticleType(request.getArticleTypeId())
                .and(ArticleSpecifications.withCategory(request.getCategoryId())));

        // 검색조건과 함께 게시글 조회
        Page<Article> articlePage = articleRepository.findAll(spec, pageable);
        List<Article> articles = articlePage.getContent();
        
        // 검색결과를 Response형태로 변환
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
                                .createDt(article.getCreateDate())
                                .isLiked(article.getLikeMemberList().stream().anyMatch(member -> member.getUid().equals(SecurityUtil.getCurrentUserIdEx().orElse(null))))
                                .build()
                        ).collect(
                                ArrayList<Art01SRArticle>::new,
                                ArrayList<Art01SRArticle>::add,
                                ArrayList<Art01SRArticle>::addAll)
                ).build();

        return response;

    }
}
