package kr.co.puerpuella.apitextssul.api.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.member.dto.request.Mem10Request;
import kr.co.puerpuella.apitextssul.api.member.dto.request.Mem14Request;
import kr.co.puerpuella.apitextssul.api.member.service.Mem14LoginService;
import kr.co.puerpuella.apitextssul.api.member.service.Mem10JoinService;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name="멤버관련 컨트롤러", description = "멤버 리소스에 접근하기 위한 컨트롤러를 정의합니다.")
@RestController
@RequestMapping("/v1/api/")
@RequiredArgsConstructor
public class MemberController extends CommonController {

    private final Mem14LoginService loginService;

    private final Mem10JoinService joinService;
    @Operation(summary = "회원 등록", description = "새로운 회원리소스를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Mem10Request.class)))
    })
    @PostMapping("/members/login")
    public ResponseEntity login(@RequestBody Mem14Request request) {
        return execute(loginService, request);
    }

    @Operation(summary = "회원 로그인", description = "전달된 로그인정보를 검사하고, 성공시 Access 토큰을 발급한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Mem14Request.class)))
    })
    @PostMapping("/members")
    public ResponseEntity join(@RequestBody Mem10Request request) {
        return execute(joinService, request);
    }

}
