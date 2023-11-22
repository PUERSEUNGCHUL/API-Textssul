package kr.co.puerpuella.apitextssul.api.image.service;

import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.model.entity.Image;
import kr.co.puerpuella.apitextssul.model.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class Img03ViewService {

    private final ImageRepository imageRepository;

    public byte[] viewImage(int imageId, String fileName) throws IOException {

        Image image = imageRepository.findByImageIdAndImageOriginalName(imageId, fileName).orElseThrow(() -> new ApplicationException(ErrorInfo.NOT_FOUND_IMAGE));


        File imageFile = new File(image.getImagePath());

        // FileInputStream을 사용하여 파일을 읽어옴
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imageFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 파일 크기만큼의 byte 배열 생성
        byte[] byteArray = new byte[(int) imageFile.length()];

        // 파일 내용을 byte 배열에 읽어옴
        fis.read(byteArray);

        return byteArray;
    }
}
