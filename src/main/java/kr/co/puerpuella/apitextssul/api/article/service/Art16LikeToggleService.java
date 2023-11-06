package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art16Request;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Art16LikeToggleService extends CommonService {

    private final ArticleRepository articleRepository;

    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Art16Request request = (Art16Request) params[0];

        switch (request.getLikeType()) {
            case 1: {

            }
            case 0: {

            }
            default : {

            }
        }

        return null;
    }
}
