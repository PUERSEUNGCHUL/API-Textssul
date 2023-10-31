package kr.co.puerpuella.apitextssul.api.article.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Builder
@Data
public class Art01Request extends CommonDTO {

    @Schema(description = "검색 페이지", nullable = false, example = "0", defaultValue = "0")
    private Integer page;

    @Schema(description = "정렬방식", nullable = false, example = "asc", defaultValue = "asc")
    private String order;

    @Schema(description = "표시 수 ", nullable = false, example = "20", defaultValue = "20")
    private Integer limit;

    private Pageable pageable;

    @Schema(description = "검색조건(작성자)", nullable = true, example = "324222")
    private Integer authorUid;

    @Schema(description = "검색조건(제목)", nullable = true, example = "똘똘이")
    private String articleTitle;

    @Schema(description = "검색조건(카테고리)", nullable = true, example = "1")
    private Integer categoryId;

    @Schema(description = "검색조건(제목)", nullable = true, example = "1")
    private Integer articleTypeId;
}
