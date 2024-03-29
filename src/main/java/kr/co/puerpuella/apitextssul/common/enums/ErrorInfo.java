package kr.co.puerpuella.apitextssul.common.enums;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorInfo {

    NOT_FOUND_ID(HttpServletResponse.SC_NOT_FOUND, "해당 리소스ID가 존재하지 않습니다."),
    NOT_FOUND_FILE(HttpServletResponse.SC_NOT_FOUND, "해당 리소스가 ID는 존재하나 실제로 존재하지 않습니다."),

    NOT_FOUND_IMAGE(HttpServletResponse.SC_NOT_FOUND, "존재하지 않는 파일입니다."),

    FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "클라이언트가 인증되지 않았거나, 유효한 인증 정보가 부족하여 요청이 거부되었습니다."),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "서버가 해당 요청을 이해했지만, 권한이 없어 요청이 거부되었습니다."),
    JOIN_DUPLICATED_NICKNAME(1001, "이미 존재하는 닉네임입니다."),
    JOIN_DUPLICATED_EMAIL(1002, "이미 존재하는 메일입니다."),
    LOGIN_NO_EMAIL(2001, "가입되어 있지 않은 메일입니다."),
    LOGIN_INVALID_PASSWORD(2002, "패스워드가 일치하지 않습니다."),


    SECURITY_INVALID_AUTHORIZED(3001, "로그인되어있지 않습니다."),
    SECURITY_ACCESS_DENIED(3002,"액세스가 거부되었습니다."),
    SECURITY_INVALID_SIGN(3011,"잘못된 JWT 서명입니다."),
    SECURITY_EXPIRED_TOKEN(3012,"만료된 JWT 토큰입니다."),
    SECURITY_NO_SUPPORT_TOKEN(3013,"지원되지 않는 JWT토큰입니다."),
    SECURITY_INVALID_TOKEN(3014,"JWT 토큰이 잘못되었습니다."),
    SECURITY_NO_TOKEN(3015,"JWT 토큰이 존재하지 않습니다."),
    SECURITY_NO_MATCH_REFRESH_TOKEN(3020,"유효하지 않은 Refresh토큰입니다."),


    TOKEN_NO_USER(4001, "존재하지 않는 유저입니다."),
    TOKEN_INVALID_USER(4002, "유효하지 않은 UID입니다"),

    ARTICLE_NO_RESOURCE(4001, "게시글이 존재하지 않습니다."),

    ARTICLE_NO_MATCH_USER(4002, "게시글 작성자와 로그인한 유저가 동일하지 않습니다."),

    COMMENT_NO_RESOURCE(5001, "답변글이 존재하지 않습니다."),

    COMMENT_NO_MATCH_USER(5002, "답변 작성자와 로그인한 유저가 동일하지 않습니다."),

    LIKE_NO_ARTICLE(6001, ""),
    LIKE_NO_TYPE(6002, "좋아요구분은 1(좋아요), 0(좋아요 취소)만 가능합니다."),
    LIKE_SAME_REQUEST(6003, "이미 처리되었습니다."),

    ORDER_TYPE_NO(9001, "정렬방식이 유효하지 않습니다."),
    ORDER_DIRECTION_NO(9002, "정렬방향이 유효하지 않습니다."),

    ;



    public final int errorCode;
    public final String errorMessage;
}
