package com.iquantex.email.common.enums;

import lombok.Getter;

@Getter
public enum StatusCodeEnum {

    /**
     *  状态码
     */
    GATEWAY_TIMEOUT(7, "访问超时", 504),
    SERVICE_UNAVAILABLE(6, "服务不可达", 503),
    FORBIDDEN(5, "访问权限不足", 403),
    BAD_REQUEST(4, "参数不符合要求", 400),
    NOT_FOUND(3, "资源不存在", 404),
    UNAUTHORIZED(2, "认证无效，用户凭据不正确", 401),
    INTERNAL_SERVER_ERROR(1, "服务器内部异常", 500),
    SUCCESS(0, "成功", 200);

    private Integer val;
    private String description;
    private Integer httpStatusCode;

    StatusCodeEnum(Integer val,String description, Integer httpStatusCode) {
        this.val = val;
        this.description = description;
        this.httpStatusCode = httpStatusCode;
    }
}
