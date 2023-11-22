package kr.co.puerpuella.apitextssul.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderType {
    DATE("date", "createDate", Comparator.comparing(Article::getCreateDate)),
    VIEW_CNT("viewCnt", "viewCnt", Comparator.comparingInt(Article::getViewCnt)),
    LIKE_CNT("likeCnt", "likeMemberCnt", Comparator.comparingInt(article -> article.getLikeMemberList().size())),
    COMMENT_CNT("commentCnt", "commentCnt", Comparator.comparingInt(article -> article.getCommentList().size())),
    DEFAULT("", "createDate", Comparator.comparing(Article::getCreateDate)),

    ;

    private final String parameterStr;
    private final String sortProperty;
    private final Comparator<Article> comparator;

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
