package kr.co.puerpuella.apitextssul.model.repositories;

import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.entity.Member;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Article findOneByArticleId(Integer ArticleId);

    List<Article> findArticlesByCreateUserUid (Integer uid);

    List<Article> findArticlesByArticleType(int articleTypeId);

    List<Article> findArticlesByArticleCategory(int categoryId);

    List<Article> findAll(Specification<Article> spec);

}
