package kr.co.puerpuella.apitextssul.common.util;


import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.exception.ValidationException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class SecurityUtil {

    public static Integer getCurrentUserId() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증정보가 없습니다.");
            new ValidationException(ErrorInfo.TOKEN_NO_USER);
        }

        String uid = null;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            uid = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            uid = (String) authentication.getPrincipal();
        }

        try {
            String uidStr = Optional.ofNullable(uid).orElseThrow(()->new ValidationException(ErrorInfo.TOKEN_NO_USER));

            return Integer.parseInt(uidStr);
        } catch (NumberFormatException e) {
            throw new ValidationException(ErrorInfo.TOKEN_INVALID_USER);
        }
    }

}
