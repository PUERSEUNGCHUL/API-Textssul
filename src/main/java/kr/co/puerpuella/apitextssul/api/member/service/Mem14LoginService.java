package kr.co.puerpuella.apitextssul.api.member.service;

import kr.co.puerpuella.apitextssul.api.member.dto.request.Mem14Request;
import kr.co.puerpuella.apitextssul.api.member.dto.response.Mem14Response;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import kr.co.puerpuella.apitextssul.model.repositories.MemberRepository;
import kr.co.puerpuella.apitextssul.security.JwtFilter;
import kr.co.puerpuella.apitextssul.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Mem14LoginService extends CommonService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /** 패스워드 암호화 모듈 */
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;


    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Mem14Request loginForm = (Mem14Request) params[0];

        Member savedMember = memberRepository.findOneByEmail(loginForm.getEmail());

        //가입되어 있는 회원인지 체크
        if (savedMember == null) {

            throw new ApplicationException(ErrorInfo.LOGIN_NO_EMAIL);
        }

        //패스워드 확인
        if (!passwordEncoder.matches(loginForm.getPassword(), savedMember.getPassword())){

            throw new ApplicationException(ErrorInfo.LOGIN_INVALID_PASSWORD);
        }

        // 신규 액세스토큰 발급
        String accessToken = createAccessToken(loginForm, savedMember);

        return Mem14Response.builder().accessToken(accessToken).uid(savedMember.getUid()).nickname(savedMember.getNickname()).build();
    }

    /**
     * 새로운 Access토큰을 생성한다.
     * @param loginForm 로그인 요청 DTO
     * @param savedMember 요청 DTO로부터 받아온 회원 리소스
     * @return 새로운 AccessToken
     */
    private String createAccessToken(Mem14Request loginForm, Member savedMember) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(savedMember.getUid(), loginForm.getPassword());

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String jwt = tokenProvider.createToken(authenticate);
        return jwt;
    }

    /**
     * UID로 회원정보를 조회한다.
     * @param uid the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {

        Member member = memberRepository.findOneByUid(Integer.parseInt(uid));

        return new User(member.getUid().toString(),member.getPassword(), member.getAuthorities());
    }
}
