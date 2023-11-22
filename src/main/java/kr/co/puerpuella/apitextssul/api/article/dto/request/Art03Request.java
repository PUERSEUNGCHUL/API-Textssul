package kr.co.puerpuella.apitextssul.api.article.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Art03Request extends CommonDTO {

    @Schema(description = "게시글 제목", nullable = false, example = "")
    private String articleTitle;

    @Schema(description = "작성자 ID", nullable = false, example = "")
    private int authorUid;

    @Schema(description = "게시글 카테고리 ID", nullable = false, example = "")
    private int categoryId;

    @Schema(description = "게시글 종류 ID", nullable = false, example = "")
    private int articleTypeId;

    @Schema(description = "본문 내용", nullable = false, example = "")
    private String articleContent;

    public Article toEntity() {
        Article article = Article.builder()
                .articleTitle(this.articleTitle)
                .articleCategory(ArticleCategory.resolve(this.categoryId))
                .articleType(ArticleType.resolve(this.articleTypeId))
                .content(this.articleContent)
                .viewCnt(0)
                .build();

        article.setCreateUser(Member.builder().uid(this.authorUid).build());
        article.setUpdateUser(Member.builder().uid(this.authorUid).build());

        return article;

    }

}
