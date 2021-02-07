package com.iquantex.email.common.config;

/**
 * @author gys
 * @date 2020/11/12
 */
public enum EmailService {

    /**
     * 默认模式，调用下游
     */
    DEFAULT,

    /**
     * 不调用下游系统，只在BOI系统
     */
    BOI_ONLY
}
