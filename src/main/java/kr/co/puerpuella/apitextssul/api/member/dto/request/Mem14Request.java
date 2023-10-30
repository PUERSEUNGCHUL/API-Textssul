package kr.co.puerpuella.apitextssul.api.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Mem14Request extends CommonDTO {

    @Schema(description = "이메일", nullable = false, example = "sample@s2ul.com")
    private String email;
    
    @Schema(description = "비밀번호", nullable = false, example = "password")
    private String password;
}
