package com.ssafy.checksource.exception;

public class UserException extends RuntimeException{
    private final com.ssafy.checksource.exception.ErrorCode errorCode;

    public  UserException(final String message, com.ssafy.checksource.exception.ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public UserException(com.ssafy.checksource.exception.ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public com.ssafy.checksource.exception.ErrorCode getErrorCode(){
        return errorCode;
    }
}
