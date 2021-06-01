package com.iquantex.email.dao.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author myli
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SYS_EMAIL_TPL")
@KeySequence(value = "S_SYS_EMAIL_TPL", clazz = Long.class)
public class SysEmailTpl implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 邮件ID
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 邮件编码
     */
    @TableField("CODE")
    private String code;

    /**
     * 标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 内容模版
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 产品收件角色
     */
    @TableField("FUND_RECEIVER_ROLE")
    private String fundReceiverRole;

    /**
     * 产品抄送角色
     */
    @TableField("FUND_CC_ROLE")
    private String fundCcRole;

    /**
     * 接收人
     */
    @TableField("RECEIVER_USER")
    private String receiverUser;

    /**
     * 抄送人
     */
    @TableField("CC_USER")
    private String ccUser;

    /**
     * 发送人
     */
    @TableField("SEND_USER")
    private String sendUser;

    /**
     * 黑名单
     */
    @TableField("BLACK_USER")
    private String blackUser;

    /**
     * 业务类型
     */
    @TableField("BUSINESS_TYPE")
    private String businessType;

    /**
     * 状态<>enable=1=启用&disable=0=禁用
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 收件角色
     */
    @TableField("RECEIVER_ROLE")
    private String receiverRole;

    /**
     * 抄送角色
     */
    @TableField("CC_ROLE")
    private String ccRole;

    /**
     * 黑名单角色
     */
    @TableField("BLACK_ROLE")
    private String blackRole;

    /**
     * 模板有效起始日
     */
    @TableField("START_DATE")
    private Date startDate;

    /**
     * 模板有效截止日
     */
    @TableField("EXPIRY_DATE")
    private Date expiryDate;

    /**
     * 邮件发送日期限制<>normal=0=每日&workday=1=法定工作日&ibtradeday=2=银行间交易日&ssetradeday=3=证交所交易日&hktradeday=4=港交所交易日
     */
    @TableField("DATE_LIMIT")
    private Integer dateLimit;

    /**
     * 发送产品范围，产品代码列表，逗号分隔
     */
    @TableField("FUND_CODES")
    private String fundCodes;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 创建者
     */
    @TableField("CREATOR")
    private Long creator;

    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    /**
     * 修改者
     */
    @TableField("MODIFIER")
    private Long modifier;


    public static final String ID = "ID";

    public static final String CODE = "CODE";

    public static final String TITLE = "TITLE";

    public static final String CONTENT = "CONTENT";

    public static final String FUND_RECEIVER_ROLE = "FUND_RECEIVER_ROLE";

    public static final String FUND_CC_ROLE = "FUND_CC_ROLE";

    public static final String RECEIVER_USER = "RECEIVER_USER";

    public static final String CC_USER = "CC_USER";

    public static final String SEND_USER = "SEND_USER";

    public static final String BLACK_USER = "BLACK_USER";

    public static final String BUSINESS_TYPE = "BUSINESS_TYPE";

    public static final String STATUS = "STATUS";

    public static final String RECEIVER_ROLE = "RECEIVER_ROLE";

    public static final String CC_ROLE = "CC_ROLE";

    public static final String BLACK_ROLE = "BLACK_ROLE";

    public static final String START_DATE = "START_DATE";

    public static final String EXPIRY_DATE = "EXPIRY_DATE";

    public static final String DATE_LIMIT = "DATE_LIMIT";

    public static final String FUND_CODES = "FUND_CODES";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATOR = "CREATOR";

    public static final String MODIFY_TIME = "MODIFY_TIME";

    public static final String MODIFIER = "MODIFIER";

}
