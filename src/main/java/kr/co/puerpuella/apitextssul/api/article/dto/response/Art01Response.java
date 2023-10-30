package kr.co.puerpuella.apitextssul.api.article.dto.response;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Art01Response extends CommonReturnData {

    /** 검색 게시글 목록 */
    private List<Art01SRArticle> articleList;
}
