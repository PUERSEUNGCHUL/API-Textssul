package kr.co.puerpuella.apitextssul.api.member.dto.result;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginDto extends CommonReturnData {

    private String accessToken;
}
