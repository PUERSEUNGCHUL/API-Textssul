package kr.co.puerpuella.apitextssul.api.article.controller;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art01Request;
import kr.co.puerpuella.apitextssul.api.article.service.Art01ArticleListViewService;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/")
@RequiredArgsConstructor
public class ArticleController extends CommonController {

    private final Art01ArticleListViewService articleListViewService;

    @GetMapping("/articles")
    public ResponseEntity<ResponseBody> listArticle(
            Art01Request request
    ) {
        return execute(articleListViewService, request);
    }

}
