package kr.co.puerpuella.apitextssul.model.repositories;

import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {
    List<ArticleComment> findAll(Specification<ArticleComment> spec);
}
