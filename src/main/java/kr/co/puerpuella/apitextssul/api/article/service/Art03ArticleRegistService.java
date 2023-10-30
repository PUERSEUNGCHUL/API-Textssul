package kr.co.puerpuella.apitextssul.api.article.service;

import jakarta.persistence.EntityManager;
import kr.co.puerpuella.apitextssul.api.article.dto.request.Art03Request;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Art03ArticleRegistService extends CommonService {

    private final EntityManager em;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art03Request request = (Art03Request) params[0];

        Article article = request.toEntity();

        em.persist(article);

        return new CommonReturnData();
    }
}
