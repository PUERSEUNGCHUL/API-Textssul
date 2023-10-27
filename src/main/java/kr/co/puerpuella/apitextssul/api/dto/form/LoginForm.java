package kr.co.puerpuella.apitextssul.api.dto.form;

import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인폼
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoginForm extends CommonDTO {

    private String email;
    private String password;
}
