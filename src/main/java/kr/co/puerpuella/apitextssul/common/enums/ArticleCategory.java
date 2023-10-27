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

    ;
    private int categoryId;
    private String categoryName;

    private static final Map<Integer, ArticleCategory> CODE_MAP = Stream.of(values()).collect(Collectors.toMap(ArticleCategory::getCategoryId, Function.identity()));

    @JsonCreator
    public static ArticleCategory resolve(Long type) {

        return Optional.ofNullable(CODE_MAP.get(type)).orElseThrow(() -> new IllegalArgumentException(type+" is invalid value"));
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
