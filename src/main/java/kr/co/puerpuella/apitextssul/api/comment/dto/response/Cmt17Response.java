package kr.co.puerpuella.apitextssul.api.comment.dto.response;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cmt17Response extends CommonReturnData {

    private int articleId;

    private int commentId;

    private int likeType;

    private int likeCnt;
    
}
