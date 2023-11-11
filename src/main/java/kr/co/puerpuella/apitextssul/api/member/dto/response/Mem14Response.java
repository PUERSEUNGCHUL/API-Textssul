package kr.co.puerpuella.apitextssul.api.member.dto.response;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Mem14Response extends CommonReturnData {

    private String accessToken;

    private Integer uid;

    private String nickname;


}
