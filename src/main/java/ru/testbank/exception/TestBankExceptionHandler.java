package ru.testbank.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.testbank.dto.TestBankResponseDto;

/**
 * Created by Admin on 08.04.2018.
 */
@ControllerAdvice
public class TestBankExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(TestBankExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public TestBankResponseDto<Object> handleException (Exception ex) {
        logger.error("handleException " ,ex);
        TestBankResponseDto<Object> response = new TestBankResponseDto<>();
        if (ex instanceof TestBankException) {
            response.setCode(((TestBankException) ex).getCode());
        } else {
            response.setCode("ERROR");
        }
        response.setMessage(ex.getMessage() + "\n" +ExceptionUtils.getStackTrace(ex));
        return response;
    }
}
