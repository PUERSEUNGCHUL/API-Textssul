package kr.co.puerpuella.apitextssul.api.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.puerpuella.apitextssul.api.article.dto.request.Art01Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.comment.dto.request.Cmt06Request;
import kr.co.puerpuella.apitextssul.api.comment.service.Cmt06CommentListViewService;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="댓글관련 컨트롤러", description = "댓글 리소스에 접근하기 위한 컨트롤러를 정의합니다.")
@RestController
@RequestMapping("/v1/api/comments")
@RequiredArgsConstructor
public class CommentController extends CommonController {

    private final Cmt06CommentListViewService commentListViewService;
    
    @Operation(summary = "댓글 목록 조회", description = "댓글 리소스에서 전달된 조건에 맞는 댓글리소스를 취합하여 전달합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Art01Response.class)))
    })
    @GetMapping()
    public ResponseEntity<ResponseBody> listComments(
            Cmt06Request request
    ) {
        return execute(commentListViewService, request);
    }
}
