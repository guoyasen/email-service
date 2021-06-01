package com.iquantex.email.param;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * 修改模版
 */
public class UpdateEmailTplInParam {
    /**
     * 邮件编码
     */
    private String code;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容模版
     */
    private String content;
    /**
     * 收件角色
     */
    private List<Long> receiverRole;
    /**
     * 抄送角色
     */
    private List<Long> ccRole;
    /**
     * 接收人
     */
    private List<String> receiverUser;
    /**
     * 抄送人
     */
    private List<String> ccUser;
    /**
     * 发送人
     */
    private String sendUser;
    /**
     * 黑名单
     */
    private List<String> blackUser;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 产品收件角色
     */
    private List<Long> fundReceiverRole;
    /**
     * 产品抄送角色
     */
    private List<Long> fundCcRole;
    /**
     * 黑名单角色
     */
    private List<Long> blackRole;
    /**
     * 有效起始日
     */
    private Date startDate;
    /**
     * 有效截止日
     */
    private Date expiryDate;
    /**
     * 邮件发送日期限制
     */
    @NotNull(message = "[邮件发送日期限制]不能为空")
    private Integer dateLimit;
    /**
     * 发送产品范围，产品代码列表
     */
    private List<String> fundCodes;

    /**
     * 邮件发送日期限制 - 每日
     */
    public static final int c_dateLimit_normal = 0;

    /**
     * 邮件发送日期限制 - 法定工作日
     */
    public static final int c_dateLimit_workday = 1;

    /**
     * 邮件发送日期限制 - 银行间交易日
     */
    public static final int c_dateLimit_ibtradeday = 2;

    /**
     * 邮件发送日期限制 - 证交所交易日
     */
    public static final int c_dateLimit_ssetradeday = 3;

    /**
     * 邮件发送日期限制 - 港交所交易日
     */
    public static final int c_dateLimit_hktradeday = 4;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Long> getReceiverRole() {
        return this.receiverRole;
    }

    public void setReceiverRole(List<Long> receiverRole) {
        this.receiverRole = receiverRole;
    }

    public List<Long> getCcRole() {
        return this.ccRole;
    }

    public void setCcRole(List<Long> ccRole) {
        this.ccRole = ccRole;
    }

    public List<String> getReceiverUser() {
        return this.receiverUser;
    }

    public void setReceiverUser(List<String> receiverUser) {
        this.receiverUser = receiverUser;
    }

    public List<String> getCcUser() {
        return this.ccUser;
    }

    public void setCcUser(List<String> ccUser) {
        this.ccUser = ccUser;
    }

    public String getSendUser() {
        return this.sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public List<String> getBlackUser() {
        return this.blackUser;
    }

    public void setBlackUser(List<String> blackUser) {
        this.blackUser = blackUser;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Long> getFundReceiverRole() {
        return this.fundReceiverRole;
    }

    public void setFundReceiverRole(List<Long> fundReceiverRole) {
        this.fundReceiverRole = fundReceiverRole;
    }

    public List<Long> getFundCcRole() {
        return this.fundCcRole;
    }

    public void setFundCcRole(List<Long> fundCcRole) {
        this.fundCcRole = fundCcRole;
    }

    public List<Long> getBlackRole() {
        return this.blackRole;
    }

    public void setBlackRole(List<Long> blackRole) {
        this.blackRole = blackRole;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getDateLimit() {
        return this.dateLimit;
    }

    public void setDateLimit(Integer dateLimit) {
        this.dateLimit = dateLimit;
    }

    public List<String> getFundCodes() {
        return this.fundCodes;
    }

    public void setFundCodes(List<String> fundCodes) {
        this.fundCodes = fundCodes;
    }
}