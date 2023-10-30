package kr.co.puerpuella.apitextssul.api.article.dto.request;

import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

/**
 * ARTICLE01
 */
@Builder
@Data
public class Art02Request extends CommonDTO {

    private Long articleId;
}
