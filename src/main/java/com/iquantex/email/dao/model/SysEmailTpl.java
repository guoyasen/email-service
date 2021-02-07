package com.iquantex.email.dao.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.JdbcType;

@TableName("SYS_EMAIL_TPL")
public class SysEmailTpl {
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

    /**
     * 内容模版
     */
    @TableField("CONTENT")
    private String content;

    public static final String TABLE = "SYS_EMAIL_TPL";

    public static final String f_id = "id";

    public static final String f_code = "code";

    public static final String f_title = "title";

    public static final String f_fundReceiverRole = "fundReceiverRole";

    public static final String f_fundCcRole = "fundCcRole";

    public static final String f_receiverUser = "receiverUser";

    public static final String f_ccUser = "ccUser";

    public static final String f_sendUser = "sendUser";

    public static final String f_blackUser = "blackUser";

    public static final String f_businessType = "businessType";

    public static final String f_status = "status";

    public static final String f_receiverRole = "receiverRole";

    public static final String f_ccRole = "ccRole";

    public static final String f_blackRole = "blackRole";

    public static final String f_startDate = "startDate";

    public static final String f_expiryDate = "expiryDate";

    public static final String f_dateLimit = "dateLimit";

    public static final String f_fundCodes = "fundCodes";

    public static final String f_createTime = "createTime";

    public static final String f_creator = "creator";

    public static final String f_modifyTime = "modifyTime";

    public static final String f_modifier = "modifier";

    public static final String f_content = "content";

    /**
     * 启用
     */
    public static final Integer c_status_enable = 1;

    /**
     * 禁用
     */
    public static final Integer c_status_disable = 0;

    /**
     * 每日
     */
    public static final Integer c_dateLimit_normal = 0;

    /**
     * 法定工作日
     */
    public static final Integer c_dateLimit_workday = 1;

    /**
     * 银行间交易日
     */
    public static final Integer c_dateLimit_ibtradeday = 2;

    /**
     * 证交所交易日
     */
    public static final Integer c_dateLimit_ssetradeday = 3;

    /**
     * 港交所交易日
     */
    public static final Integer c_dateLimit_hktradeday = 4;

    /**
     * 获取邮件ID
     *
     * @return ID - 邮件ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置邮件ID
     *
     * @param id 邮件ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取邮件编码
     *
     * @return CODE - 邮件编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置邮件编码
     *
     * @param code 邮件编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取标题
     *
     * @return TITLE - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取产品收件角色
     *
     * @return FUND_RECEIVER_ROLE - 产品收件角色
     */
    public String getFundReceiverRole() {
        return fundReceiverRole;
    }

    /**
     * 设置产品收件角色
     *
     * @param fundReceiverRole 产品收件角色
     */
    public void setFundReceiverRole(String fundReceiverRole) {
        this.fundReceiverRole = fundReceiverRole == null ? null : fundReceiverRole.trim();
    }

    /**
     * 获取产品抄送角色
     *
     * @return FUND_CC_ROLE - 产品抄送角色
     */
    public String getFundCcRole() {
        return fundCcRole;
    }

    /**
     * 设置产品抄送角色
     *
     * @param fundCcRole 产品抄送角色
     */
    public void setFundCcRole(String fundCcRole) {
        this.fundCcRole = fundCcRole == null ? null : fundCcRole.trim();
    }

    /**
     * 获取接收人
     *
     * @return RECEIVER_USER - 接收人
     */
    public String getReceiverUser() {
        return receiverUser;
    }

    /**
     * 设置接收人
     *
     * @param receiverUser 接收人
     */
    public void setReceiverUser(String receiverUser) {
        this.receiverUser = receiverUser == null ? null : receiverUser.trim();
    }

    /**
     * 获取抄送人
     *
     * @return CC_USER - 抄送人
     */
    public String getCcUser() {
        return ccUser;
    }

    /**
     * 设置抄送人
     *
     * @param ccUser 抄送人
     */
    public void setCcUser(String ccUser) {
        this.ccUser = ccUser == null ? null : ccUser.trim();
    }

    /**
     * 获取发送人
     *
     * @return SEND_USER - 发送人
     */
    public String getSendUser() {
        return sendUser;
    }

    /**
     * 设置发送人
     *
     * @param sendUser 发送人
     */
    public void setSendUser(String sendUser) {
        this.sendUser = sendUser == null ? null : sendUser.trim();
    }

    /**
     * 获取黑名单
     *
     * @return BLACK_USER - 黑名单
     */
    public String getBlackUser() {
        return blackUser;
    }

    /**
     * 设置黑名单
     *
     * @param blackUser 黑名单
     */
    public void setBlackUser(String blackUser) {
        this.blackUser = blackUser == null ? null : blackUser.trim();
    }

    /**
     * 获取业务类型
     *
     * @return BUSINESS_TYPE - 业务类型
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置业务类型
     *
     * @param businessType 业务类型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * 获取状态<>enable=1=启用&disable=0=禁用
     *
     * @return STATUS - 状态<>enable=1=启用&disable=0=禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态<>enable=1=启用&disable=0=禁用
     *
     * @param status 状态<>enable=1=启用&disable=0=禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取收件角色
     *
     * @return RECEIVER_ROLE - 收件角色
     */
    public String getReceiverRole() {
        return receiverRole;
    }

    /**
     * 设置收件角色
     *
     * @param receiverRole 收件角色
     */
    public void setReceiverRole(String receiverRole) {
        this.receiverRole = receiverRole == null ? null : receiverRole.trim();
    }

    /**
     * 获取抄送角色
     *
     * @return CC_ROLE - 抄送角色
     */
    public String getCcRole() {
        return ccRole;
    }

    /**
     * 设置抄送角色
     *
     * @param ccRole 抄送角色
     */
    public void setCcRole(String ccRole) {
        this.ccRole = ccRole == null ? null : ccRole.trim();
    }

    /**
     * 获取黑名单角色
     *
     * @return BLACK_ROLE - 黑名单角色
     */
    public String getBlackRole() {
        return blackRole;
    }

    /**
     * 设置黑名单角色
     *
     * @param blackRole 黑名单角色
     */
    public void setBlackRole(String blackRole) {
        this.blackRole = blackRole == null ? null : blackRole.trim();
    }

    /**
     * 获取模板有效起始日
     *
     * @return START_DATE - 模板有效起始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置模板有效起始日
     *
     * @param startDate 模板有效起始日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取模板有效截止日
     *
     * @return EXPIRY_DATE - 模板有效截止日
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * 设置模板有效截止日
     *
     * @param expiryDate 模板有效截止日
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 获取邮件发送日期限制<>normal=0=每日&workday=1=法定工作日&ibtradeday=2=银行间交易日&ssetradeday=3=证交所交易日&hktradeday=4=港交所交易日
     *
     * @return DATE_LIMIT - 邮件发送日期限制<>normal=0=每日&workday=1=法定工作日&ibtradeday=2=银行间交易日&ssetradeday=3=证交所交易日&hktradeday=4=港交所交易日
     */
    public Integer getDateLimit() {
        return dateLimit;
    }

    /**
     * 设置邮件发送日期限制<>normal=0=每日&workday=1=法定工作日&ibtradeday=2=银行间交易日&ssetradeday=3=证交所交易日&hktradeday=4=港交所交易日
     *
     * @param dateLimit 邮件发送日期限制<>normal=0=每日&workday=1=法定工作日&ibtradeday=2=银行间交易日&ssetradeday=3=证交所交易日&hktradeday=4=港交所交易日
     */
    public void setDateLimit(Integer dateLimit) {
        this.dateLimit = dateLimit;
    }

    /**
     * 获取发送产品范围，产品代码列表，逗号分隔
     *
     * @return FUND_CODES - 发送产品范围，产品代码列表，逗号分隔
     */
    public String getFundCodes() {
        return fundCodes;
    }

    /**
     * 设置发送产品范围，产品代码列表，逗号分隔
     *
     * @param fundCodes 发送产品范围，产品代码列表，逗号分隔
     */
    public void setFundCodes(String fundCodes) {
        this.fundCodes = fundCodes == null ? null : fundCodes.trim();
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建者
     *
     * @return CREATOR - 创建者
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * 获取修改时间
     *
     * @return MODIFY_TIME - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取修改者
     *
     * @return MODIFIER - 修改者
     */
    public Long getModifier() {
        return modifier;
    }

    /**
     * 设置修改者
     *
     * @param modifier 修改者
     */
    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取内容模版
     *
     * @return CONTENT - 内容模版
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容模版
     *
     * @param content 内容模版
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}