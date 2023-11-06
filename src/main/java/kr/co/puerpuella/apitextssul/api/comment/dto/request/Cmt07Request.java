package kr.co.puerpuella.apitextssul.api.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleCommentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cmt07Request extends CommonDTO {


    @Schema(description = "게시글 ID", nullable = false, example = "1")
    private Integer articleId;

    @Schema(description = "답변", nullable = false, example = "안녕하세요")
    private String comment;

    public ArticleComment toEntity() {

        ArticleComment articleComment = ArticleComment.builder()
                .article(Article.builder().articleId(this.articleId).build())
                .comment(this.comment)
                .build();

        articleComment.setCreateUser(Member.builder().uid(SecurityUtil.getCurrentUserId()).build());
        articleComment.setCreateDate(new Date());
        articleComment.setCreateIP(SecurityUtil.getUserIP());

        return articleComment;
    }

}
