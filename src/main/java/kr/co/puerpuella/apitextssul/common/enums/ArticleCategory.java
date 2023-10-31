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
public enum ArticleCategory {

    BASIC(1, "일반"),
    HUMOR(2, "유머"),
    POLITICS(3, "정치"),
    ERROR(999, "에러용")
    
    ;
    private int categoryId;
    private String categoryName;

    private static final Map<Integer, ArticleCategory> CODE_MAP = Stream.of(values()).collect(Collectors.toMap(ArticleCategory::getCategoryId, Function.identity()));

    @JsonCreator
    public static ArticleCategory resolve(Integer type) {

        return Optional.ofNullable(CODE_MAP.get(type)).orElse(ERROR);
    }

    public static ArticleCategory valueOf(Integer categoryId) {

        for (ArticleCategory item : ArticleCategory.values()) {

            if (item.categoryId == categoryId) {
                return item;
            }
        }
        return null;
    }
}
