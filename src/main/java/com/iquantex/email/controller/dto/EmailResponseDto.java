package com.iquantex.email.controller.dto;

import java.io.Serializable;

public class EmailResponseDto<T> implements Serializable {
    private String code;

    private String msg;

    private String detail;

    private Object head;

    private EmailResponseDto<T> body;
}
