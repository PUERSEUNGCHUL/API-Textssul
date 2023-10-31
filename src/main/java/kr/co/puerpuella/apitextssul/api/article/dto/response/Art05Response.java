package kr.co.puerpuella.apitextssul.api.article.dto.response;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Art05Response extends CommonReturnData {

    private Integer articleId;
    
}
