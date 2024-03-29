package kr.co.puerpuella.apitextssul.api.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.puerpuella.apitextssul.api.article.dto.request.Art16Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art02Response;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art16Response;
import kr.co.puerpuella.apitextssul.api.comment.dto.request.*;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.*;
import kr.co.puerpuella.apitextssul.api.comment.service.*;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="댓글관련 컨트롤러", description = "댓글 리소스에 접근하기 위한 컨트롤러를 정의합니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/articles")
public class ArticleCommentController extends CommonController {

    private final Cmt06CommentListViewService commentListViewService;

    private final Cmt07CommentRegistryService commentRegistryService;

    private final Cmt08CommentEditService commentEditService;

    private final Cmt09CommentDeleteService commentDeleteService;

    private final Cmt17LikeToggleService likeToggleService;
    
    @Operation(summary = "댓글 목록 조회", description = "댓글 리소스에서 전달된 조건에 맞는 댓글리소스를 취합하여 전달합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cmt06Response.class)))
    })
    @GetMapping("/{articleId}/comments")
    public ResponseEntity<ResponseBody> listComments(
            @PathVariable("articleId") Integer articleId,
            Cmt06Request request
    ) {
        request.setArticleId(articleId);
        return execute(commentListViewService, request);
    }

    @Operation(summary = "댓글 작성", description = "댓글 리소스에 새로운 댓글을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cmt07Response.class)))
    })
    @PostMapping("/{articleId}/comments")
    public ResponseEntity<ResponseBody> registryComment(
            @PathVariable("articleId") Integer articleId,
            @RequestBody Cmt07Request request
    ) {
        request.setArticleId(articleId);
        return execute(commentRegistryService, request);
    }

    @Operation(summary = "댓글 수정", description = "댓글 리소스에 기존 리소스를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cmt08Response.class)))
    })
    @PatchMapping("/{articleId}/comments/{commentId}")
    public ResponseEntity<ResponseBody> editComment(
            @PathVariable("articleId") Integer articleId,
            @PathVariable("commentId") Integer commentId,
            @RequestBody Cmt08Request request
    ) {

        request.setArticleId(articleId);
        request.setCommentId(commentId);

        return execute(commentEditService, request);
    }

    @Operation(summary = "댓글 삭제", description = "댓글 리소스에 해당 리소스를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cmt09Response.class)))
    })
    @DeleteMapping("/{articleId}/comments/{commentId}")
    public ResponseEntity<ResponseBody> deleteComment(
            @PathVariable("articleId") Integer articleId,
            @PathVariable("commentId") Integer commentId,
            @RequestBody Cmt09Request request
    ) {

        request.setArticleId(articleId);
        request.setCommentId(commentId);

        return execute(commentDeleteService, request);
    }

    @Operation(summary = "게시글답글 좋아요 기능", description = "답글 리소스에 좋아요를 추가/삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글답글 좋아요 토글 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cmt17Response.class)))
    })
    @PatchMapping("/{articleId}/{commentId}/{likeType}")
    public ResponseEntity<ResponseBody> likeArticle(
            @PathVariable("articleId") Integer articleId,
            @PathVariable("commentId") Integer commentId,
            @PathVariable("likeType") Integer likeType
    ) {
        return execute(likeToggleService, Cmt17Request.builder().articleId(articleId).commentId(commentId).likeType(likeType).build());
    }


}
