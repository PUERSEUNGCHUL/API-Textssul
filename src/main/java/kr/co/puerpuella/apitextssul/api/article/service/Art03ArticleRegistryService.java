package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art03Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art03Response;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.Image;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import kr.co.puerpuella.apitextssul.model.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Art03ArticleRegistryService extends CommonService {


    private final ArticleRepository articleRepository;

    private final ImageRepository imageRepository;

    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art03Request request = (Art03Request) params[0];

        request.setAuthorUid(SecurityUtil.getCurrentUserId());

        Article newArticle = articleRepository.save(request.toEntity());

        for (Integer imageId : request.getImageIdList()) {

            Image ti = imageRepository.findById(imageId).orElseThrow(() -> new ApplicationException(ErrorInfo.NOT_FOUND_FILE));

            ti.setArticle(newArticle);

            imageRepository.save(ti);

        }

        return Art03Response.builder().articleId(newArticle.getArticleId()).build();
    }
}
