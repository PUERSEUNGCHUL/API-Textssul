
package kr.co.puerpuella.apitextssul.common.framework.exception;

import kr.co.puerpuella.apitextssul.common.enums.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException{

    private ErrorInfo errorInfo;
}
