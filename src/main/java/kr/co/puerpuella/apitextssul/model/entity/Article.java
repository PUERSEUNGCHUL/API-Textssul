package kr.co.puerpuella.apitextssul.model.entity;

import jakarta.persistence.*;
import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="T_ARTICLE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    private Long articleId;

    @Column(name="ARTICLE_TITLE")
    private String articleTitle;

    @Column(name = "ARTICLE_CATEGORY_ID")
    private ArticleCategory articleCategory;

    @Column(name = "ARTICLE_TYPE_ID")
    private ArticleType articleType;

    @Column(name="VIEW_CNT")
    private int viewCnt;

    //TODO Article은 임시임
    @OneToMany(mappedBy = "articleId", fetch = FetchType.LAZY)
    private List<Article> likeList = new ArrayList<>();

    //TODO Article은 임시임
    @OneToMany(mappedBy = "articleId", fetch = FetchType.LAZY)
    private List<Article> commentList = new ArrayList<>();

    private String content;





}
