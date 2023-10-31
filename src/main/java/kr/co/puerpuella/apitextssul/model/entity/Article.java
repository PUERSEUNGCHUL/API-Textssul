package kr.co.puerpuella.apitextssul.model.entity;

import jakarta.persistence.*;
import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import kr.co.puerpuella.apitextssul.common.enums.convertor.ArticleCategoryConvertor;
import kr.co.puerpuella.apitextssul.common.enums.convertor.ArticleTypeConvertor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TB_ARTICLE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Comment("게시글")
public class Article extends LabelEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    @Comment("게시글 ID")
    private Integer articleId;

    @Column(name="ARTICLE_TITLE")
    @Comment("게시글 제목")
    private String articleTitle;

    @Column(name = "ARTICLE_TYPE_ID")
    @Comment("게시글 종류 ID")
    @Convert(converter = ArticleTypeConvertor.class)
    private ArticleType articleType;

    @Column(name = "ARTICLE_CATEGORY_ID")
    @Comment("게시글 카테고리 ID")
    @Convert(converter = ArticleCategoryConvertor.class)
    private ArticleCategory articleCategory;

    @Comment("본문")
    @Column(name="CONTENT")
    private String content;

    @Column(name="VIEW_CNT")
    @Comment("조회수")
    private int viewCnt;

    @ManyToMany
    @JoinTable(name = "TMP_ARTICLE_LIKE",
            joinColumns = @JoinColumn(name = "ARTICLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CREATE_UID")
    )
    @Comment("좋아요 회원 목록")
    private List<Member> likeMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    @Comment("댓글 목록")
    private List<ArticleComment> commentList = new ArrayList<>();

}
