package kr.co.puerpuella.textssul.api.controller;

import kr.co.puerpuella.textssul.api.dto.TestDTO;
import kr.co.puerpuella.textssul.api.dto.form.JoinForm;
import kr.co.puerpuella.textssul.api.dto.form.LoginForm;
import kr.co.puerpuella.textssul.api.service.LoginService;
import kr.co.puerpuella.textssul.api.service.MemberJoinService;
import kr.co.puerpuella.textssul.common.framework.CommonController;
import kr.co.puerpuella.textssul.common.framework.CommonService;
import kr.co.puerpuella.textssul.common.framework.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
