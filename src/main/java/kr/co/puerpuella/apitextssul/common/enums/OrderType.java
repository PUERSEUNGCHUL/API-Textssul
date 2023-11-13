package kr.co.puerpuella.apitextssul.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderType {
    DATE("date", "createDate"),
    VIEW_CNT("viewCnt", "viewCnt"),
    LIKE_CNT("likeCnt", "likeMemberList"),
    COMMENT_CNT("commentCnt", "commentList"),
    DEFAULT("", "createDate"),

    ;

    private final String parameterStr;

    private final String sortProperty;

    @JsonCreator
    public static OrderType resolve(String parameterStr) {

        return Arrays.stream(values()).filter(orderType -> orderType.getParameterStr().equals(parameterStr)).findFirst().orElseThrow(() -> new ApplicationException(ErrorInfo.ORDER_TYPE_NO));
    }

    @JsonValue
    public String getValue() {
        return this.parameterStr;
    }

    public static void main(String[] args) {
        System.out.println("args = " + OrderType.resolve(""));
        ;
    }
}
