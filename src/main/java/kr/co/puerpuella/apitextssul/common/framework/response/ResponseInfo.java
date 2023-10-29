package kr.co.puerpuella.apitextssul.common.framework.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseInfo {
    private int responseCode;

    private String responseMsg;
}
