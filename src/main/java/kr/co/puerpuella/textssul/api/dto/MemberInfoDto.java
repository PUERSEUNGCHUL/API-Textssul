package kr.co.puerpuella.textssul.api.dto;

import kr.co.puerpuella.textssul.common.enums.Roles;

import kr.co.puerpuella.textssul.common.framework.response.CommonReturnData;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto extends CommonReturnData {

    //UID
    private Long uid;

    //메일주소
    private String email;

    private String nickname;

    private Roles role;
}
