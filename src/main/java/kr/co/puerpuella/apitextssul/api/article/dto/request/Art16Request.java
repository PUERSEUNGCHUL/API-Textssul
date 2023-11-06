package kr.co.puerpuella.apitextssul.api.article.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Art16Request extends CommonDTO {

    @Schema(description = "조회 대상 게시글 ID(URL Path variable)", nullable = false, example = "1244")
    private int articleId;

    @Schema(description = "좋아요토글키 (1:좋아요, 0:좋아요취소)(URL Path variable)", nullable = false, example = "1244")
    private int likeType;
}
