package kr.co.puerpuella.apitextssul.api.article.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * ARTICLE01
 */
@Builder
@Data
public class Art01SRSearch {

    /** 검색 조건 */
    private String searchType;

    /** 검색어 */
    private String searchContent;
}
