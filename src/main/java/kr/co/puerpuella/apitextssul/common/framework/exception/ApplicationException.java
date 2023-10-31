
package kr.co.puerpuella.apitextssul.common.framework.exception;

import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException{

    public ApplicationException(ErrorInfo errorInfo) {
        super(errorInfo.errorMessage);
        this.errorInfo = errorInfo;
    }

    private ErrorInfo errorInfo;
}
