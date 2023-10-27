
package kr.co.puerpuella.textssul.common.framework.exception;

import kr.co.puerpuella.textssul.common.enums.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException{

    private ErrorInfo errorInfo;
}
