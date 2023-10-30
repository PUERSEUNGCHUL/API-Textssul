package kr.co.puerpuella.apitextssul.api.member.dto.result;

import kr.co.puerpuella.apitextssul.common.enums.Roles;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto extends CommonReturnData {

    //UID
    private Integer uid;

    //메일주소
    private String email;

    private String nickname;

    private Roles role;
}
