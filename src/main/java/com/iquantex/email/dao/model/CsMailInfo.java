package com.iquantex.email.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * <p>
 * 邮件发送信息表
 * </p>
 *
 * @author myli
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CS_MAIL_INFO")
public class CsMailInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 邮件ID
     */
    @TableId(value = "MAIL_ID", type = IdType.INPUT)
    private String mailId;

    /**
     * 计划发送时间
     */
    @TableField("PLAN_TIME")
    private Date planTime;

    /**
     * 收发类型
     */
    @TableField("MAIL_TYPE")
    private String mailType;

    /**
     * 业务类型
     */
    @TableField("BUSINESS_TYPE")
    private String businessType;

    /**
     * 发送人
     */
    @TableField("SENDER")
    private String sender;

    /**
     * 接收人
     */
    @TableField("RECEIVERS")
    private String receivers;

    /**
     * 抄送人
     */
    @TableField("C_COPY")
    private String cCopy;

    /**
     * 邮件主题
     */
    @TableField("SUBJECT")
    private String subject;

    /**
     * 邮件类型
     */
    @TableField("CONTENT_TYPE")
    private String contentType;

    /**
     * 状态
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 创建人
     */
    @TableField("CREATOR")
    private String creator;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * ip地址
     */
    @TableField("HOST_IP")
    private String hostIp;

    /**
     * 业务编码
     */
    @TableField("BUSINESS_CODE")
    private String businessCode;

    /**
     * 文件数量
     */
    @TableField("FILE_COUNT")
    private Integer fileCount;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 邮件内容
     */
    @TableField("CONTENT")
    private byte[] content;

    /**
     * 发送次数
     */
    @TableField("SEND_NUMBER")
    private Integer sendNumber;


    public static final String MAIL_ID = "MAIL_ID";

    public static final String PLAN_TIME = "PLAN_TIME";

    public static final String MAIL_TYPE = "MAIL_TYPE";

    public static final String BUSINESS_TYPE = "BUSINESS_TYPE";

    public static final String SENDER = "SENDER";

    public static final String RECEIVERS = "RECEIVERS";

    public static final String C_COPY = "C_COPY";

    public static final String SUBJECT = "SUBJECT";

    public static final String CONTENT_TYPE = "CONTENT_TYPE";

    public static final String STATUS = "STATUS";

    public static final String CREATOR = "CREATOR";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String HOST_IP = "HOST_IP";

    public static final String BUSINESS_CODE = "BUSINESS_CODE";

    public static final String FILE_COUNT = "FILE_COUNT";

    public static final String REMARK = "REMARK";

    public static final String CONTENT = "CONTENT";

    public static final String SEND_NUMBER = "SEND_NUMBER";

}
