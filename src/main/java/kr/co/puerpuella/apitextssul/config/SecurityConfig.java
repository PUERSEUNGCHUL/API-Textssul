package kr.co.puerpuella.apitextssul.config;

import kr.co.puerpuella.apitextssul.security.JwtAccessDeniedHandler;
import kr.co.puerpuella.apitextssul.security.JwtAuthenticationEntryPoint;
import kr.co.puerpuella.apitextssul.security.JwtSecurityConfig;
import kr.co.puerpuella.apitextssul.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * BCrypt암호화 알고리즘을 이용한 암호화 변환기
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {

        httpSecurity
                //csrf 설정 :: disable
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling(authenticationManager -> authenticationManager
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )

                .sessionManagement(sessionManager -> {
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })

                .authorizeHttpRequests(authorizeHttpRequests -> {

                    authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll();

                    authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/v1/api/articles/**").permitAll();
                    authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/v1/api/articles/**").authenticated();
                    authorizeHttpRequests.requestMatchers(HttpMethod.PATCH, "/v1/api/articles/**").authenticated();
                    authorizeHttpRequests.requestMatchers(HttpMethod.DELETE, "/v1/api/articles/**").authenticated();

                    authorizeHttpRequests
                            .requestMatchers(new MvcRequestMatcher(introspector, "/v1/api/articles")).permitAll();
                    authorizeHttpRequests.anyRequest().permitAll();
                })

                .apply(new JwtSecurityConfig(tokenProvider))
        ;


        return httpSecurity.build();
    }
}
