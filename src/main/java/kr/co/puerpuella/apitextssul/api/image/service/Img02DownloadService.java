package kr.co.puerpuella.apitextssul.api.image.service;

import kr.co.puerpuella.apitextssul.api.image.dto.request.Img02Request;
import kr.co.puerpuella.apitextssul.api.image.dto.response.Img02Response;
import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.CommonDTO;
import kr.co.puerpuella.apitextssul.common.framework.CommonService;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.util.ImageUtil;
import kr.co.puerpuella.apitextssul.model.entity.Image;
import kr.co.puerpuella.apitextssul.model.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
@Slf4j
public class Img02DownloadService extends CommonService {

    private final ImageUtil imageUtil;

    private final ImageRepository imageRepository;

    @Override
    protected CommonReturnData execute(CommonDTO... params) {

        Img02Request request = (Img02Request) params[0];

        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new ApplicationException(ErrorInfo.NOT_FOUND_ID));

        File file = new File(image.getImagePath());

        log.debug(file.getAbsolutePath());

        try {
            byte[] fileByes = Files.readAllBytes(file.toPath());

            return Img02Response.builder()
                    .image(fileByes)
                    .fileName(image.getImageOriginalName())
                    .fileExtension(image.getImageExtension())
                    .build();
        } catch (IOException e) {
            throw new ApplicationException(ErrorInfo.NOT_FOUND_FILE);
        }
    }
}
