package kr.co.puerpuella.apitextssul.api.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cmt09Request extends CommonDTO {


    @Schema(description = "게시글 ID", nullable = false, example = "1")
    private Integer articleId;

    @Schema(description = "답변 ID", nullable = false, example = "1")
    private Integer commentId;


}
