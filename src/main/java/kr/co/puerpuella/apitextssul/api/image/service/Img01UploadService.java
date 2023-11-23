package kr.co.puerpuella.apitextssul.api.image.service;

import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.api.image.dto.request.Img01Request;
import kr.co.puerpuella.apitextssul.api.image.dto.response.Img01Response;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.util.ImageUtil;
import kr.co.puerpuella.apitextssul.model.entity.Image;
import kr.co.puerpuella.apitextssul.model.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 이미지 등록 서비스
 * 전달 받은 파라미터(multipart)의 내용을 서버에 물리저장하고, 파일에 ID를 부여하고 부여한 ID를 응답객체에 전달한다.
 */
@Service
@RequiredArgsConstructor
public class Img01UploadService extends CommonService {

    private final ImageUtil imageUtil;

    private final ImageRepository imageRepository;

    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Img01Request request = (Img01Request) params[0];

        MultipartFile multiPartFile = request.getFile();

        File newFile;
        String filePath;
        try {
            filePath = imageUtil.saveBlankFile();
            newFile = new File(filePath);
            multiPartFile.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image image = Image.builder()
                .imagePath(filePath)
                .imageExtension(getExtensionNm(multiPartFile.getOriginalFilename()))
                .imageSize(multiPartFile.getSize())
                .imageOriginalName(multiPartFile.getOriginalFilename())
                .imageName(newFile.getName())
        .build();

        Image newImage = imageRepository.save(image);



        return Img01Response.builder().imageId(newImage.getImageId()).fileName(newImage.getImageOriginalName()).build();
    }

    /**
     * 파일 명으로부터 확장자를 추출한다.
     * @param fileName 파일명
     * @return 확장자명
     */
    private String getExtensionNm(String fileName) {
        Path path = Paths.get(fileName);
        return  path.getFileName().toString().contains(".") ?
                path.getFileName().toString().substring(path.getFileName().toString().lastIndexOf(".") + 1) :
                "";
    }
}
