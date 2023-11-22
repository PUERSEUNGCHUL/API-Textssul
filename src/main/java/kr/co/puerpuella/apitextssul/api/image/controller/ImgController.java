package kr.co.puerpuella.apitextssul.api.image.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.puerpuella.apitextssul.api.image.dto.request.*;
import kr.co.puerpuella.apitextssul.api.image.service.Img01UploadService;
import kr.co.puerpuella.apitextssul.api.image.service.Img02DownloadService;
import kr.co.puerpuella.apitextssul.api.image.service.Img03ViewService;
import kr.co.puerpuella.apitextssul.common.framework.CommonController;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseBody;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@RestController
@RequestMapping("/v1/api/images")
@RequiredArgsConstructor
@Tag(name="이미지 컨트롤러", description = "이미지 리소스에 접근하기 위한 컨트롤러를 정의합니다.")
public class ImgController extends CommonController {

    private final Img01UploadService img01Service;

    private final Img02DownloadService img02Service;
    private final Img03ViewService img03Service;

    @Operation(summary = "이미지 등록", description = "전달받은 멀티파트타입 이미지를 등록하고, ID를 부여한다.")
    @ApiResponses({
            @ApiResponse(description = "등록 성공", responseCode = "200", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Img01Request.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseBody> uploadImage(
        @RequestParam("image") MultipartFile image
    ) {

        return execute(img01Service, Img01Request.builder().file(image).build());
    }
    @Operation(summary = "이미지 다운로드", description = "해당 리소스를 다운로드한다.")
    @ApiResponses({
            @ApiResponse(description = "다운로드 성공", responseCode = "200", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Img02Request.class)))
    })
    @GetMapping("{imageId}")
    public ResponseEntity<?> downloadImage(
        @PathVariable("imageId") int imageId
    ) {
        return execute(img02Service, Img02Request.builder().imageId(imageId).build());
    }

    @Operation(summary = "이미지 표시(직접)", description = "해당 리소스를 이미지 컨텐츠로 반환한다.")
    @ApiResponses({
            @ApiResponse(description = "이미지 조회 성공", responseCode = "200", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Img02Request.class)))
    })
    @GetMapping("{imageId}/{fileName}")
    public ResponseEntity<?> viewImage(

            @PathVariable("imageId") int imageId,
            @PathVariable("fileName") String fileName
    ) {
        byte[] data;
        try {

            data = img03Service.viewImage(imageId, fileName);
        } catch (ApplicationException e) {

            return ResponseEntity.badRequest().body(ResponseBody.builder().responseData(new CommonReturnData()).responseInfos(Collections.singletonList(ResponseInfo.builder().responseCode(e.getErrorInfo().errorCode).responseMsg(e.getErrorInfo().errorMessage).build())).build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseBody.builder().responseData(new CommonReturnData()).responseInfos(Collections.singletonList(ResponseInfo.builder().responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).responseMsg(e.getMessage()).build())).build());

        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(data)
                ;
        //return execute(img02Service, Img02Request.builder().imageId(imageId).build());
    }
}
