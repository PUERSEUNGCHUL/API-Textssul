package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art02Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art02Response;
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

/**
 * 게시글 상세조회 서비스
 *
 * 게시글 리소스로부터 전달된 파라미터의 검색조건에 적합한 리소스를 반환한다.
 * see also
 */
@Service
@RequiredArgsConstructor
public class Art02ArticleViewService extends CommonService {

    private final ArticleRepository articleRepository;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art02Request request = (Art02Request) params[0];
        Art02Response response = new Art02Response();

        Article article = articleRepository.findOneByArticleId(request.getArticleId());

        if (article == null) {
            throw new ApplicationException(ErrorInfo.ARTICLE_NO_RESOURCE);
        }

        response.setLiked(setLikeOnLoginUser(article));

        article.addViewCnt();

        articleRepository.save(article);

        response.convertEntityToDto(articleRepository.findOneByArticleId(request.getArticleId()));

        return response;
    }

    private boolean setLikeOnLoginUser(Article article) {
        long likedMemberCnt = article.getLikeMemberList().stream().filter((m) -> {
            try {
                return m.getUid().equals(SecurityUtil.getCurrentUserId());
            } catch (ApplicationException e) {
                return false;
            }
        }).count();

        return likedMemberCnt > 0;
    }
}
