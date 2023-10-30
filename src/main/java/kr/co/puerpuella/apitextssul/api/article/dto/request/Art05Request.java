package kr.co.puerpuella.apitextssul.api.article.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Art05Request extends CommonDTO {

    @Schema(description = "조회 대상 게시글 ID", nullable = false, example = "1244")
    private int articleId;
}
