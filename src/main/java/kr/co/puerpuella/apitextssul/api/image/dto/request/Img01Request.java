package kr.co.puerpuella.apitextssul.api.image.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Img01Request extends CommonDTO {

    @Schema(description = "이미지 정보", nullable = false)
    private MultipartFile file;
}
