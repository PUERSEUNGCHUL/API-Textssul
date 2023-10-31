package kr.co.puerpuella.apitextssul.api.comment.service;

import kr.co.puerpuella.apitextssul.api.article.dto.request.Art01Request;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01Response;
import kr.co.puerpuella.apitextssul.api.article.dto.response.Art01SRArticle;
import kr.co.puerpuella.apitextssul.api.comment.dto.request.Cmt06Request;
import kr.co.puerpuella.apitextssul.api.comment.dto.response.Cmt06Response;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import kr.co.puerpuella.apitextssul.model.repositories.ArticleRepository;
import kr.co.puerpuella.apitextssul.model.repositories.spec.ArticleSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 목록 조회 서비스
 *
 * 게시글 리소스로부터 전달된 파라미터의 검색조건에 적합한 리소스를 반환한다.
 * see also
 */
@Service
@RequiredArgsConstructor
public class Cmt06CommentListViewService extends CommonService {

    private final ArticleRepository articleRepository;
    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Cmt06Request request = (Cmt06Request) params[0];


        Cmt06Response response = new Cmt06Response();

        return response;

    }
}
