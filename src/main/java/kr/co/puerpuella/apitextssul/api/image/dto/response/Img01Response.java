package kr.co.puerpuella.apitextssul.api.image.dto.response;

import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Img01Response extends CommonReturnData {

    private Integer imageId;
}
