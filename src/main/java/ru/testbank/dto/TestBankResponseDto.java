package ru.testbank.dto;

/**
 * Created by Admin on 08.04.2018.
 */
public class TestBankResponseDto<T> {
    private String code = "OK";
    private String message = "";
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
