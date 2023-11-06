package kr.co.puerpuella.apitextssul.api.article.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Art16Response extends CommonReturnData {

    private int articleId;

    private int likeType;

    private int likeCnt;
    
}
