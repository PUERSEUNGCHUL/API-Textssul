package kr.co.puerpuella.apitextssul.common.util;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class SecurityUtil {

    public static Optional<String> getCurrentUserId() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증정보가 없습니다.");
            return Optional.empty();
        }

        String uid = null;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            uid = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            uid = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(uid);
    }

}
