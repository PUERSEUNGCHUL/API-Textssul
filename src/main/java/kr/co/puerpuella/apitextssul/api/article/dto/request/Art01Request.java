package kr.co.puerpuella.apitextssul.api.article.dto.request;

import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * ARTICLE01
 */
@Builder
@Data
public class Art01Request extends CommonDTO {

    /** 검색 시작 위치 */
    private Integer offset = 0;

    /** 정렬방식(asc, desc) */
    private String order = "asc";

    /** 표시 수 */
    private Integer limit = 20;

    /** 검색조건(작성자) */
    private Long autorUid;

    /** 검색조건(제목) */
    private String articleTitle;

    /** 검색조건(카테고리) */
    private Integer categoryId;

    /** 검색조건(제목) */
    private Integer articleTypeId;
}
