package br.com.stgenerator.util;

public class ResponseUtil {

    private String message;

    public ResponseUtil(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}