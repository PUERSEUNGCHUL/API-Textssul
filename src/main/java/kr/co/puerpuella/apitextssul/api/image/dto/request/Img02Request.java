package kr.co.puerpuella.apitextssul.api.image.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Img02Request extends CommonDTO {

    @Schema(description = "불러올 이미지 ID", nullable = false)
    private int imageId;
}
