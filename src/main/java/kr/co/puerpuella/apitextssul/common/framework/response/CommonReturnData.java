package kr.co.puerpuella.apitextssul.common.framework.response;

import lombok.Data;

import java.util.Date;

/**
 * Front단에 전달할 응답데이터의 부모객체
 * 각각 응답데이터를 하나의 집합으로 표시하기 위함.
 */
@Data
public class CommonReturnData {
    private Date responseDate = new Date();
}
