package ru.testbank.exception;

/**
 * Created by Admin on 08.04.2018.
 */
public class TestBankException extends RuntimeException {

    public TestBankException() {
    }

    public TestBankException(String code, String message) {
        super(message);
        this.code = code;
    }

    public TestBankException(String message) {
        super(message);
    }

    public TestBankException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestBankException(Throwable cause) {
        super(cause);
    }

    public TestBankException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
