package kr.co.puerpuella.apitextssul.api.article.dto.response;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Art02Response extends CommonReturnData {

    /** 게시글 ID */
    private int articleId;

    /** 게시글 제목 */
    private String articleTitle;

    /** 작성자 ID */
    private int authorUid;

    /** 작성자 닉네임 */
    private String authorNick;

    /** 게시글 카테고리 ID */
    private int categoryId;

    /** 게시글 카테고리명 */
    private String categoryNm;

    /** 게시글 카테고리 ID */
    private int articleTypeId;

    /** 게시글 카테고리명 */
    private String articleTypeNm;

    /** 조회수 */
    private int viewCnt;

    /** 게시글 좋아요 수 */
    private int likeCnt;

    /** 게시글 코멘트 수 */
    private int commentCnt;

    /** 썸네일 이미지 ID */
    private int thumbnailImageId;

    /** 썸네일 이미지 바이트 */
    private String thumbnailImagePath;

    /** 본문 내용 */
    private String articleContent;

    /** 좋아요 여부 */
    private boolean isLiked;

    private List<String> imagePathList;

    public void convertEntityToDto(Article article) {

        this.articleId = article.getArticleId();
        this.articleTitle = article.getArticleTitle();
        this.authorUid = article.getCreateUser().getUid();
        this.authorNick = article.getCreateUser().getNickname();
        this.categoryId = article.getArticleCategory().getCategoryId();
        this.categoryNm = article.getArticleCategory().getCategoryName();
        this.articleTypeId = article.getArticleType().getTypeId();
        this.articleTypeNm = article.getArticleType().getTypeName();
        this.viewCnt = article.getViewCnt();
        this.commentCnt = article.getCommentList().size();
        this.likeCnt = article.getLikeMemberList().size();
        this.articleContent = article.getContent();

    }
}
