package kr.co.puerpuella.apitextssul.api.comment.service;

import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01SRArticle;
import kr.co.puerpuella.apitextssul.api.comment.dto.request.Cmt06Request;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt06Response;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt06SRComment;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleCommentRepository;
import kr.co.puerpuella.apitextssul.model.repositories.spec.ArticleCommentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 답변 목록 조회 서비스
 *
 * 답변 리소스로부터 전달된 파라미터의 검색조건에 적합한 리소스를 반환한다.
 * see also
 */
@Service
@RequiredArgsConstructor
public class Cmt06CommentListViewService extends CommonService {

    private final ArticleCommentRepository articleCommentRepository;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Cmt06Request request = (Cmt06Request) params[0];

        // 답변 검색 조건 설정
        Specification<ArticleComment> spec = Specification.where(ArticleCommentSpecifications.withUserUid(request.getAuthorUid()))
                .and(ArticleCommentSpecifications.withArticleId(request.getArticleId()));

        return Cmt06Response.builder().commentList(articleCommentRepository.findAll(spec).stream().map((ac) ->
            Cmt06SRComment.builder()
                            .articleId(ac.getArticle().getArticleId())
                            .commentId(ac.getCommentId())
                            .comment(ac.getComment())
                            .authorUid(ac.getCreateUser().getUid())
                            .authorNick(ac.getCreateUser().getNickname())
                            .createDt(ac.getCreateDate())
                            .likeCnt(ac.getLikeMemberList().size())
                    .build()
        ).collect(
                ArrayList<Cmt06SRComment>::new,
                ArrayList<Cmt06SRComment>::add,
                ArrayList<Cmt06SRComment>::addAll)).build();

    }
}
