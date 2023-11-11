package kr.co.puerpuella.apitextssul.api.article.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * ARTICLE01
 */
@Builder
@Data
public class Art01SRArticle{

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

    /** 썸네일 이미지 주소 */
    private String thumbnailImagePath;

    private Date createDt;

    private boolean isLiked;
}
