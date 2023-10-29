package kr.co.puerpuella.apitextssul.api.article.dto.response;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * ARTICLE01
 */
@Builder
@Data
public class Art01Response extends CommonReturnData {

    private List<Art01SRArticle> articleList;
}
