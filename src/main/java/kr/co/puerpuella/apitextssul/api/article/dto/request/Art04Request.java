package kr.co.puerpuella.apitextssul.api.article.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Art04Request extends CommonDTO {

    @Schema(description = "게시글 ID", nullable = true, example = "")
    private int articleId;

    @Schema(description = "게시글 제목", nullable = false, example = "")
    private String articleTitle;

    @Schema(description = "작성자 ID", nullable = false, example = "")
    private int authorUid;

    @Schema(description = "게시글 카테고리 ID", nullable = false, example = "")
    private int categoryId;

    @Schema(description = "게시글 카테고리 ID", nullable = false, example = "")
    private int articleTypeId;

    @Schema(description = "게시글 본문", nullable = false, example = "")
    private String articleContent;

}
