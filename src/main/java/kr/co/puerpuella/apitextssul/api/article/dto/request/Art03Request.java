package kr.co.puerpuella.apitextssul.api.article.dto.request;

import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Art03Request extends CommonDTO {

    /**
     * 요청파라미터를 Entity로 변경해주는 메서드
     * @return
     */
    public Article toEntity() {

        return new Article();
    }
}
