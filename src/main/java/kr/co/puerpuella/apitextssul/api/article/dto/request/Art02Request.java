package kr.co.puerpuella.apitextssul.api.article.dto.request;

import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Art02Request extends CommonDTO {

    /** 조회 대상 게시글ID */
    private Integer articleId;
}
