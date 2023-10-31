package kr.co.puerpuella.apitextssul.api.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cmt06Request extends CommonDTO {

    @Schema(description = "게시글 ID", nullable = true, example = "0", defaultValue = "0")
    private Integer articleId;

    @Schema(description = "작성자 UID", nullable = true, example = "0", defaultValue = "0")
    private Integer authorUid;

}
