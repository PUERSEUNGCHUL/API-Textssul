package kr.co.puerpuella.apitextssul.api.article.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.puerpuella.apitextssul.api.article.dto.request.*;
import kr.co.puerpuella.apitextssul.api.article.dto.response.*;
import kr.co.puerpuella.apitextssul.api.article.service.*;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import kr.co.puerpuella.apitextssul.common.framework.exception.ValidationException;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="게시글관련 컨트롤러", description = "게시글 리소스에 접근하기 위한 컨트롤러를 정의합니다.")
@RestController
@RequestMapping("/v1/api/")
@RequiredArgsConstructor
public class ArticleController extends CommonController {

    private final Art01ArticleListViewService articleListViewService;

    private final Art02ArticleViewService articleViewService;

    private final Art03ArticleRegistService articleRegistService;

    private final Art04ArticleEditService art04ArticleEditService;

    private final Art05ArticleDeleteService articleDeleteService;



    @Operation(summary = "게시글 목록 조회", description = "게시글 리소스에서 전달된 조건에 맞는 게시글리소스를 취합하여 전달합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Art01Response.class)))
    })
    @GetMapping("/articles")
    public ResponseEntity<ResponseBody> listArticles(
            Art01Request request,
            Pageable pageable
    ) {

        request.setPageable(pageable);

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

    @Operation(summary = "게시글 신규 등록", description = "전달된 데이터를 게시글리소스에 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Art03Response.class)))
    })
    @PostMapping("/articles")
    public ResponseEntity<ResponseBody> registArticle(
            @RequestBody Art03Request request
            ) {
        request.setAuthorUid(SecurityUtil.getCurrentUserId());
        return execute(articleRegistService, request);
    }

    @Operation(summary = "게시글 수정", description = "전달된 데이터를 게시글 리소스로부터 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Art04Response.class)))
    })
    @PatchMapping("/articles/{articleId}")
    public ResponseEntity<ResponseBody> editArticle(
            @PathVariable("articleId") Integer articleId,
            @RequestBody Art04Request request
    ) {
        request.setArticleId(articleId);
        return execute(art04ArticleEditService, request);
    }

    @Operation(summary = "게시글 삭제", description = "전달된 데이터를 게시글 리소스로부터 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Art05Response.class)))
    })
    @DeleteMapping("/articles/{articleId}")
    public ResponseEntity<ResponseBody> deleteArticle(
            @PathVariable("articleId") Integer articleId
    ) {
        return execute(articleDeleteService, Art05Request.builder().articleId(articleId).build());
    }
}
