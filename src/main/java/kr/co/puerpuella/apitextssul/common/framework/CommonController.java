package kr.co.puerpuella.apitextssul.common.framework;


import jakarta.servlet.http.HttpServletResponse;
import kr.co.puerpuella.apitextssul.common.framework.exception.ApplicationException;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseBody;
import kr.co.puerpuella.apitextssul.common.framework.response.ResponseInfo;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

/**
 * Rest컨트롤러의 공통기능
 *
 * 모든 컨트롤러는 execute를 return하여 사용하도록 유도
 *
 */
public class CommonController {

    protected ResponseEntity<ResponseBody> execute(CommonService service, CommonDTO param) {
        return getResponseBody(service, new CommonDTO[] {param});
    }


    /**
     * 
     * @param service 실행시킬 서비스
     * @param params 전달할 파라미터
     * @return Front단에 전달할 응답객체
     */
    protected ResponseEntity<ResponseBody> execute(CommonService service, CommonDTO ...params) {

        return getResponseBody(service, params);

    }

    private static ResponseEntity<ResponseBody> getResponseBody(CommonService service, CommonDTO[] params) {
        ResponseBody responseBody;

        try {

            //파라미터로 전달되는 서비스를 실행
            CommonReturnData returnData = service.execute(params);

            // 서비스의 결과값을 ResponseBody로 감싼다.
            responseBody = ResponseBody.builder()
                    .responseData(returnData)
                    .responseInfos(Collections.singletonList(ResponseInfo.builder().responseCode(HttpServletResponse.SC_OK).build()))
                    .build();

            return ResponseEntity.ok().body(responseBody);
        } catch (ApplicationException e) {
            // 서비스의 실행중 ValidationException이 발생한경우 (입력받은 Form데이터가 서비스실행의 문제가 되는 경우 발생한다.)

            // 에러정보를 ResponseBody로 감싼다.
            responseBody = ResponseBody.builder()
                    .responseInfos(Collections.singletonList(
                                    ResponseInfo.builder()
                                            .responseCode(e.getErrorInfo().errorCode)
                                            .responseMsg(e.getErrorInfo().errorMessage)
                                            .build()
                            )
                    )
                    .build();

            return ResponseEntity.ok().body(responseBody);

        }
    }
}
