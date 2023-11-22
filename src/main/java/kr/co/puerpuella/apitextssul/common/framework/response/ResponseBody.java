package kr.co.puerpuella.apitextssul.common.framework.response;

import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * responseData
 *   Front단에 전달해야할 내용 (status가 1인 경우는 null/ status가 정상인 경우에도  null인 경우 존재)
 * responseInfo
 *   Front단에 전달할 에러정보 (치명적인 에러나 경고포함)
 */
@AllArgsConstructor
@Builder
@Getter
public class ResponseBody {

    private CommonReturnData responseData;

    private List<ResponseInfo> responseInfos;

}