package kr.co.puerpuella.apitextssul.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderDirection {

    ASC("asc", Sort.Direction.ASC),
    DESC("desc", Sort.Direction.DESC),
    DEFAULT("", Sort.Direction.DESC),
    ;

    private final String parameterStr;
    private final Sort.Direction direction;

    private static final Map<String, OrderDirection> CODE_MAP = Stream.of(values()).collect(Collectors.toMap(OrderDirection::getParameterStr, Function.identity()));

    @JsonCreator
    public static OrderDirection resolve(String parameterStr) {
        return Arrays.stream(values()).filter(orderDirection -> orderDirection.getParameterStr().equals(parameterStr)).findFirst().orElseThrow(() -> new ApplicationException(ErrorInfo.ORDER_DIRECTION_NO));

    }

    @JsonValue
    public String getValue() {
        return this.parameterStr;
    }


}
