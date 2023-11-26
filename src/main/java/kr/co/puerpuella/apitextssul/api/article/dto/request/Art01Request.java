package kr.co.puerpuella.apitextssul.api.article.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.co.puerpuella.apitextssul.common.enums.OrderDirection;
import kr.co.puerpuella.apitextssul.common.enums.OrderType;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;


@EqualsAndHashCode(callSuper = true)
@Data
public class Art01Request extends CommonDTO {

    @Schema(description = "검색 페이지", nullable = false, example = "0", defaultValue = "0")
    private Integer page = 0;

    @Schema(description = "정렬방식(date:날짜순, viewCnt:조회수순, likeCnt:좋아요순, commentCnt:댓글순)", nullable = false, example = "date")
    @JsonProperty("orderType")
    private OrderType orderType = OrderType.DEFAULT;

    @Schema(description = "정렬순서(asc:오름차순, desc:내림차순)", nullable = false, example = "asc", defaultValue = "desc")
    private OrderDirection orderDirection = OrderDirection.DEFAULT;

    @Schema(description = "표시 수 ", nullable = false, example = "20", defaultValue = "20")
    private Integer limit = 20;

    @Schema(description = "검색조건(작성자)", nullable = true, example = "324222")
    private Integer authorUid;

    @Schema(description = "검색조건(제목)", nullable = true, example = "똘똘이")
    private String articleTitle;

    @Schema(description = "검색조건(카테고리)", nullable = true, example = "1")
    private Integer categoryId;

    @Schema(description = "검색조건(글타입)", nullable = true, example = "1")
    private Integer articleTypeId;

    @Schema(description = "검색조건(본문)", nullable = true, example = "모험")
    private String articleContent;
}
