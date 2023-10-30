package kr.co.puerpuella.apitextssul.api.member.controller;

import kr.co.puerpuella.apitextssul.api.member.dto.request.Mem10Request;
import kr.co.puerpuella.apitextssul.api.member.dto.request.Mem14Request;
import kr.co.puerpuella.apitextssul.api.member.service.Mem14LoginService;
import kr.co.puerpuella.apitextssul.api.member.service.Mem10JoinService;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController extends CommonController {

    private final Mem14LoginService loginService;

    private final Mem10JoinService joinService;

    @PostMapping("/v1/api/members/login")
    public ResponseEntity login(@RequestBody Mem14Request loginForm) {
        return execute(loginService, loginForm);
    }

    @PostMapping("/v1/api/members")
    public ResponseEntity join(@RequestBody Mem10Request joginForm) {
        return execute(joinService, joginForm);
    }

}
