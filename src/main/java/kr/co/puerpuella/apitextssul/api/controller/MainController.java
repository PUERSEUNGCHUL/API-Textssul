package kr.co.puerpuella.apitextssul.api.controller;

import kr.co.puerpuella.apitextssul.api.dto.form.JoinForm;
import kr.co.puerpuella.apitextssul.api.dto.form.LoginForm;
import kr.co.puerpuella.apitextssul.api.service.LoginService;
import kr.co.puerpuella.apitextssul.api.service.MemberJoinService;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController extends CommonController {

    private final LoginService loginService;

    private final MemberJoinService joinService;

    @PostMapping("/v1/api/members/login")
    public ResponseBody login(@RequestBody LoginForm loginForm) {
        return execute(loginService, loginForm);
    }

    @PostMapping("/v1/api/members")
    public ResponseBody join(@RequestBody JoinForm joginForm) {
        return execute(joinService, joginForm);
    }

}
