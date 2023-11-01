package kr.co.puerpuella.apitextssul.api.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Cmt08Request extends CommonDTO {


    @Schema(description = "게시글 ID", nullable = false, example = "1")
    private Integer articleId;

    @Schema(description = "답변 ID", nullable = false, example = "1")
    private Integer commentId;

    @Schema(description = "답변", nullable = false, example = "변경될 코멘트")
    private String comment;


}
