package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art01Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01SRArticle;
import kr.co.puerpuella.apitextssul.common.dto.Com01Image;
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
import kr.co.puerpuella.apitextssul.model.repositories.ImageRepository;
import kr.co.puerpuella.apitextssul.model.repositories.spec.ArticleSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    private final ImageRepository imageRepository;

    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art01Request request = (Art01Request) params[0];

        // 목록 페이지 조건 설정
        Pageable pageable = PageRequest.of(
                request.getPage(), request.getLimit(),
                Sort.by(request.getOrderDirection().getDirection(), request.getOrderType().getSortProperty()));

        // 게시글 검색 조건 설정
        Specification<Article> spec = Specification.where(ArticleSpecifications.withUserUid(request.getAuthorUid()))
                .and(ArticleSpecifications.withArticleType(request.getArticleTypeId())
                .and(ArticleSpecifications.withCategory(request.getCategoryId())
                .and(ArticleSpecifications.withTitle(request.getArticleTitle())
                .and(ArticleSpecifications.withContent(request.getArticleContent())
                .and(ArticleSpecifications.withCommentByUid(request.getCommentByUid()))))));

        // 검색조건과 함께 게시글 조회
        Page<Article> articlePage = articleRepository.findAll(spec, pageable);
        List<Article> articles = articlePage.getContent();

        Random r = new Random();
        // 검색결과를 Response형태로 변환
        return Art01Response.builder()
                .articleList(articles.stream().map((article) -> {
                    int randomIdx = r.nextInt(10) + 1;
                    return Art01SRArticle.builder()
                                            .articleId(article.getArticleId())
                                            .articleTypeId(article.getArticleType().getTypeId())
                                            .articleTypeNm(article.getArticleType().getTypeName())
                                            .categoryId(article.getArticleCategory().getCategoryId())
                                            .categoryNm(article.getArticleCategory().getCategoryName())
                                            .articleTitle(article.getArticleTitle())
                                            .authorUid(article.getCreateUser().getUid())
                                            .authorNick(article.getCreateUser().getNickname())
                                            .viewCnt(article.getViewCnt())
                                            .likeCnt(article.getLikeMemberCnt())
                                            .commentCnt(article.getCommentCnt())
                                            .thumbnailImage(Com01Image.builder()
                                                    .imageId(article.getThumbnailImageId())
                                                    .filePath("/v1/api/images/"+article.getThumbnailImageId()+"/c"+article.getThumbnailImageId()+".jpg")
                                                    .build())
                                            .createDt(article.getCreateDate())
                                            .isLiked(article.getLikeMemberList().stream().anyMatch(member -> member.getUid().equals(SecurityUtil.getCurrentUserIdEx().orElse(null))))
                                            .build();
                                }
                        ).collect(
                                ArrayList<Art01SRArticle>::new,
                                ArrayList<Art01SRArticle>::add,
                                ArrayList<Art01SRArticle>::addAll)
                ).build();

    }
}
