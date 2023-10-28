package kr.co.puerpuella.apitextssul.api.member.controller;

import kr.co.puerpuella.apitextssul.api.member.dto.form.JoinForm;
import kr.co.puerpuella.apitextssul.api.member.dto.form.LoginForm;
import kr.co.puerpuella.apitextssul.api.member.service.Mem14_LoginService;
import kr.co.puerpuella.apitextssul.api.member.service.Mem10_JoinService;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController extends CommonController {

    private final Mem14_LoginService loginService;

    private final Mem10_JoinService joinService;

    @PostMapping("/v1/api/members/login")
    public ResponseBody login(@RequestBody LoginForm loginForm) {
        return execute(loginService, loginForm);
    }

    @PostMapping("/v1/api/members")
    public ResponseBody join(@RequestBody JoinForm joginForm) {
        return execute(joinService, joginForm);
    }

}
