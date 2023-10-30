package kr.co.puerpuella.apitextssul.api.article.dto.request;

import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.model.entity.Article;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Art03Request extends CommonDTO {

    public Article toEntity() {

        return new Article();
    }
}
