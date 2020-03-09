package com.jingchengsoft.dzjplatform.http;

/**
 * @author xxh
 * @date 2019/11/13
 * @desc TODO.
 */
public class CommonException {
    public static final String EXCEPTION = "Exception";
    public static final String API_EXCEPTION = "Api_Exception";
    private String type;


    public CommonException(String type, Exception exception) {
        this.type = type;
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    private Exception exception;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
