package kr.co.puerpuella.apitextssul.api.article.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.puerpuella.apitextssul.api.article.dto.request.Art01Request;
import kr.co.puerpuella.apitextssul.api.article.dto.request.Art02Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art02Response;
import kr.co.puerpuella.apitextssul.api.article.service.Art01ArticleListViewService;
import kr.co.puerpuella.apitextssul.api.article.service.Art02ArticleViewService;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="게시글관련 컨트롤러", description = "게시글 리소스에 접근하기 위한 컨트롤러를 정의합니다.")
@RestController
@RequestMapping("/v1/api/")
@RequiredArgsConstructor
public class ArticleController extends CommonController {

    private final Art01ArticleListViewService articleListViewService;

    private final Art02ArticleViewService articleViewService;



    @Operation(summary = "게시글 목록 조회", description = "게시글 리소스에서 전달된 조건에 맞는 게시글리소스를 취합하여 전달합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Art01Response.class)))
    })
    @GetMapping("/articles")
    public ResponseEntity<ResponseBody> listArticles(
            Art01Request request
    ) {
        return execute(articleListViewService, request);
    }

    @Operation(summary = "게시글 상세 조회", description = "전달된 게시글ID에 해당하는 게시글의 리소스를 전달합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Art02Response.class)))
    })
    @GetMapping("/articles/{articleId}")
    public ResponseEntity<ResponseBody> viewArticle(
            @PathVariable("articleId") Integer articleId
    ) {
        return execute(articleViewService, Art02Request.builder().articleId(articleId).build());
    }

}
