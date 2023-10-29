package kr.co.puerpuella.apitextssul.api.article.service;

import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01SRArticle;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Art01ArticleListViewService extends CommonService {
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        List<Art01SRArticle> articleList = new ArrayList<>();

        articleList.add(Art01SRArticle.builder()
                .articleId(1)
                .articleTitle("똘똘이의 모험")
                .authorNick("똘똘이")
                .authorUid(123141)
                .categoryId(1)
                .categoryNm("유머")
                .articleTypeId(1)
                .articleTypeNm("텍스트")
                .viewCnt(321)
                .likeCnt(22)
                .commentCnt(2)
                .thumbnailImageId(2)
                .build());
        articleList.add(Art01SRArticle.builder()
                .articleId(2)
                .articleTitle("똘똘이의 모험2")
                .authorNick("똘똘이")
                .authorUid(123141)
                .categoryId(1)
                .categoryNm("유머")
                .articleTypeId(1)
                .articleTypeNm("텍스트")
                .viewCnt(321)
                .likeCnt(22)
                .commentCnt(2)
                .thumbnailImageId(2)
                .build());

        return Art01Response.builder().articleList(articleList).build();

    }
}
