package kr.co.puerpuella.apitextssul.api.comment.service;

import kr.co.puerpuella.apitextssul.api.comment.dto.request.Cmt08Request;
import kr.co.puerpuella.apitextssul.api.comment.dto.request.Cmt09Request;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt08Response;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt09Response;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.util.SecurityUtil;
import kr.co.puerpuella.apitextssul.model.entity.ArticleComment;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 답변 삭제 서비스
 *
 * see also
 */
@Service
@RequiredArgsConstructor
public class Cmt09CommentDeleteService extends CommonService {

    private final ArticleCommentRepository articleCommentRepository;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Cmt09Request request = (Cmt09Request) params[0];

        ArticleComment articleComment = articleCommentRepository.findById(request.getCommentId()).orElseThrow(()->new ApplicationException(ErrorInfo.SECURITY_EXPIRED_TOKEN.COMMENT_NO_RESOURCE));

        //작성자와 로그인한 유저가 같은지 확인
        if (!articleComment.getCreateUser().getUid().equals(SecurityUtil.getCurrentUserId())){

            throw new ApplicationException(ErrorInfo.COMMENT_NO_MATCH_USER);
        }

        articleCommentRepository.delete(articleComment);
        return Cmt09Response.builder().build();

    }
}
