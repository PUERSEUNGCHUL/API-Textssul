package kr.co.puerpuella.apitextssul.model.entity;

import jakarta.persistence.*;
import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TB_COMMENT")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleComment extends LabelEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Integer commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @Column(name="COMMENT")
    private String comment;

    @ManyToMany
    @JoinTable(name = "TMP_COMMENT_LIKE",
            joinColumns = @JoinColumn(name = "COMMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CREATE_UID")
    )
    @Comment("좋아요 회원 목록")
    private List<Member> likeMemberList = new ArrayList<>();

    public void update(String comment) {

        this.comment = comment;

        super.setUpdateDate(new Date());
        super.setUpdateUser(Member.builder().uid(SecurityUtil.getCurrentUserId()).build());
        super.setUpdateIP(SecurityUtil.getUserIP());
    }
}
