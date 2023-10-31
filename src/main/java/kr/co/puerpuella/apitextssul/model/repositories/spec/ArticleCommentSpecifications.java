package kr.co.puerpuella.apitextssul.model.repositories.spec;

import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import org.springframework.data.jpa.domain.Specification;

public interface ArticleCommentSpecifications {

    static Specification<ArticleComment> withArticleId(Integer articleId) {
        return (root, query, builder) -> {
            if (articleId == null) {
                return null;
            }
            return builder.equal(root.get("article").get("articleId"), articleId);
        };
    }

    static Specification<ArticleComment> withUserUid(Integer userUid) {
        return (root, query, builder) -> {
            if (userUid == null) {
                return null; // 조건을 무시
            }
            return builder.equal(root.get("createUser").get("uid"), userUid);
        };
    }

}