package com.iquantex.email.exception;

import com.iquantex.email.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private int status;
    public CommonException(ExceptionEnum em){
        super(em.getMessage());
        this.status=em.getStatus();
    }
    public CommonException(ExceptionEnum em, String msg){
        super(msg);
        this.status=em.getStatus();
    }
    public CommonException(ExceptionEnum em, Throwable cause){
        super(em.getMessage(),cause);
        this.status=em.getStatus();
    }
}
