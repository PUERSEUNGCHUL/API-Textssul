package kr.co.puerpuella.apitextssul.model.entity;

import jakarta.persistence.*;
import kr.co.puerpuella.apitextssul.common.enums.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 회원 테이블 엔티티
 */
@Entity
@Table(name="TB_MEMBER")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends LabelEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private Integer uid;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private Roles role;

    /**
     * 권한 컬렉션 생성
     *
     * @return ROLE_가 붙은 권한정보
     * Spring Security에서 사용하기 위해 맞는 양식으로 변환해 준다.
     * 현재 권한은 한개만 가지기 때문에, 본인의 권한만 리스트에 요소로 넣어준다.
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();

        // 권한을 넣어준다. 권한이 여러개라면 여러개 넣어줄 수도 있다.
        collect.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        return collect;
    }
}
