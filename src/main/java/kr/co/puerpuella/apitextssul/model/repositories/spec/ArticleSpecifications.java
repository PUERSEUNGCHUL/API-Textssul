package kr.co.puerpuella.apitextssul.model.repositories.spec;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import org.springframework.data.jpa.domain.Specification;

public interface ArticleSpecifications {

    static Specification<Article> withUserUid(Integer userUid) {
        return (root, query, builder) -> {
            if (userUid == null) {
                return null; // 조건을 무시
            }
            return builder.equal(root.get("createUser").get("uid"), userUid);
        };
    }

    static Specification<Article> withArticleType(Integer articleType) {
        return (root, query, builder) -> {
            if (articleType == null) {
                return null; // 조건을 무시
            }
            return builder.equal(root.get("articleType"), ArticleType.resolve(articleType));
        };
    }

    static Specification<Article> withCategory(Integer category) {
        return (root, query, builder) -> {
            if (category == null) {
                return null; // 조건을 무시
            }
            return builder.equal(root.get("articleCategory"), ArticleCategory.resolve(category));
        };
    }

    static Specification<Article> withTitle(String articleTitle) {
        return (root, query, criteriaBuilder) -> {
            if (articleTitle == null) {
                return null;
            }

            return criteriaBuilder.like(root.get("articleTitle"), articleTitle);
        };
    }

    static Specification<Article> withContent(String articleContent) {
        return (root, query, criteriaBuilder) -> {
            if (articleContent == null) {
                return null;
            }

            return criteriaBuilder.like(root.get("articleContent"), articleContent);
        };
    }

    static Specification<Article> withCommentByUid(Integer uid) {
        return (root, query, criteriaBuilder) -> {
            if (uid == null) {
                return null;
            }

            Join<Article, ArticleComment> commentJoin = root.join("commentList", JoinType.LEFT);
            Join<ArticleComment, Member> memberJoin = commentJoin.join("likeMemberList", JoinType.LEFT);

            return criteriaBuilder.equal(commentJoin.get("createUser").get("uid"), uid);
        };
    }
}