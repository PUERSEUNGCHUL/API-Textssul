package kr.co.puerpuella.apitextssul.common.framework;


import jakarta.transaction.Transactional;
import kr.co.puerpuella.apitextssul.common.framework.response.CommonReturnData;

/**
 * 서비스의 집합
 */
@Transactional
public abstract class CommonService {

    /**
     * 서비스 실행구문 추상화메서드
     * @param params Font단에서 전달되는 파라미터
     * @return 응답데이터
     */
    protected abstract CommonReturnData execute(CommonDTO... params);
}
