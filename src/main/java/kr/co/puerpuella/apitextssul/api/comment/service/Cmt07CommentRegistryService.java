package kr.co.puerpuella.apitextssul.api.comment.service;

import kr.co.puerpuella.apitextssul.api.comment.dto.request.Cmt06Request;
import kr.co.puerpuella.apitextssul.api.comment.dto.request.Cmt07Request;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt06Response;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt06SRComment;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt07Response;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleCommentRepository;
import kr.co.puerpuella.apitextssul.model.repositories.spec.ArticleCommentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 답변 등록 서비스
 *
 * see also
 */
@Service
@RequiredArgsConstructor
public class Cmt07CommentRegistryService extends CommonService {

    private final ArticleCommentRepository articleCommentRepository;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Cmt07Request request = (Cmt07Request) params[0];

        ArticleComment newArticleComment = articleCommentRepository.save(request.toEntity());

        return Cmt07Response.builder().articleId(newArticleComment.getArticle().getArticleId()).commentId(newArticleComment.getCommentId()).build();

    }
}
