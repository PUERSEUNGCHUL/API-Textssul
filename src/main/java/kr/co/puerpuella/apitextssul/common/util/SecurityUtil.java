package kr.co.puerpuella.apitextssul.common.util;


import jakarta.servlet.http.HttpServletResponse;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class SecurityUtil {

    public static Optional<Integer> getCurrentUserIdEx() {

        try {
            return Optional.of(getCurrentUserId());

        } catch (ApplicationException e) {
            return Optional.empty();
        }
    }
    public static Integer getCurrentUserId() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증정보가 없습니다.");
            throw new ApplicationException(ErrorInfo.TOKEN_NO_USER);
        }

        String uid = null;

        if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            uid = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            uid = (String) authentication.getPrincipal();
        }

        try {
            String uidStr = Optional.ofNullable(uid).orElseThrow(()->new ApplicationException(ErrorInfo.TOKEN_NO_USER));

            return Integer.parseInt(uidStr);
        } catch (NumberFormatException e) {
            throw new ApplicationException(ErrorInfo.TOKEN_INVALID_USER);
        }
    }

    public static void setResponse(int status, HttpServletResponse response, ErrorInfo errorInfo) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        response.getWriter().println("{ \"responseData\" : {},"
                + "\"responseInfo\" : {"
                + "\"responseCode\" : " + errorInfo.errorCode + ","
                + "\"responseMsg\" : \"" + errorInfo.errorMessage + "\""
                + "}");
    }

    public static String getUserIP() {

        return "127.0.0.1";
    }
}
