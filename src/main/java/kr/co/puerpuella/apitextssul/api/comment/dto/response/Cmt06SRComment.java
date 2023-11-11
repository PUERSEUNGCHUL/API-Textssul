package kr.co.puerpuella.apitextssul.api.comment.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Cmt06SRComment {

    private Integer articleId;

    private Integer commentId;

    private String comment;

    private int authorUid;

    private String authorNick;

    private Date createDt;

    private int likeCnt;

    private boolean isLiked;

}
