package kr.co.puerpuella.apitextssul.api.member.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.enums.Roles;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Mem10Request extends CommonDTO {

    @Schema(description = "이메일", nullable = false, example = "sample@s2ul.com")
    private String email;

    @Schema(description = "닉네임", nullable = false, example = "똘똘이")
    private String nickname;

    @Schema(description = "비밀번호", nullable = false, example = "password")
    private String password;

    public void encryptPassword(PasswordEncoder passwordEncoder) {

        this.password = passwordEncoder.encode(this.password);
    }

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .role(Roles.MEMBER)

                .build();
    }
}
