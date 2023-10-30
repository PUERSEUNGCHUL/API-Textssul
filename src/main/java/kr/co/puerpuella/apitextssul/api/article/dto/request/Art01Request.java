package kr.co.puerpuella.apitextssul.api.article.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Art01Request extends CommonDTO {

    @Schema(description = "검색 시작 위치", nullable = false, example = "20", defaultValue = "20")
    /** 검색 시작 위치 */
    private Integer offset = 0;

    /** 정렬방식(asc, desc) */
    private String order = "asc";

    /** 표시 수 */
    private Integer limit = 20;

    /** 검색조건(작성자) */
    private Integer authorUid;

    /** 검색조건(제목) */
    private String articleTitle;

    /** 검색조건(카테고리) */
    private Integer categoryId;

    /** 검색조건(제목) */
    private Integer articleTypeId;
}
