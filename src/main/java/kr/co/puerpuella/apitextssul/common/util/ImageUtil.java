package kr.co.puerpuella.apitextssul.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;

@Component
public class ImageUtil {

    private final String IMG_PATH;

    public ImageUtil(@Value("${kr.co.puerpuella.apitextssul.image_path}") String masterPath) {
        this.IMG_PATH = masterPath;
    }

    /**
     * 현재 시간에 맞는 이미지 저장소 디렉토리를 신설한다.(/{rootPath}/2014/03)
     * @param fileName 저장할 파일이름
     * @return 저장된 파일의 경로를 포함한 풀패스
     */
    public String saveBlankFile() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        String sessionID = request.getSession().getId();

        Calendar c = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        sb.append(IMG_PATH);
        sb.append("/");
        sb.append(c.get(Calendar.YEAR));
        sb.append("/");
        sb.append(String.format("%02d",c.get(Calendar.MONTH)+1));
        sb.append("/");

        File directory = new File(sb.toString());

        Files.createDirectory(directory.toPath());


        sb.append(c.getTimeInMillis());
        System.out.println("sb.toString() = " + sb.toString());


        File newFile = new File(sb.toString());

        newFile.createNewFile();

        return newFile.getAbsolutePath();


    }

}
