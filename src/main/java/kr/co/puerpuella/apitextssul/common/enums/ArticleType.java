package kr.co.puerpuella.apitextssul.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Getter
@AllArgsConstructor
public enum ArticleType {

    BASIC(1, "기본");
    ;
    private int typeId;
    private String typeName;
    private static final Map<Integer, ArticleType> CODE_MAP = Stream.of(values()).collect(Collectors.toMap(ArticleType::getTypeId, Function.identity()));

    @JsonCreator
    public static ArticleType resolve(Integer type) {

        return Optional.ofNullable(CODE_MAP.get(type)).orElse(null);
    }

    public static ArticleType valueOf(Integer typeId) {

        for (ArticleType item : ArticleType.values()) {

            if (item.typeId == typeId) {
                return item;
            }
        }
        return null;
    }
}
