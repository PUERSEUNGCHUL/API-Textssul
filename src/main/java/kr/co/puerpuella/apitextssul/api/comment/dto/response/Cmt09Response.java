package kr.co.puerpuella.apitextssul.api.comment.dto.response;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cmt09Response extends CommonReturnData {
    /** 게시글 ID */
    private Integer articleId;
    /** 답변 ID */
    private Integer commentId;
}
