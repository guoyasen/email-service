package com.iquantex.email.service;

import com.iquantex.email.param.AddEmailTplInParam;
import com.iquantex.email.param.UpdateEmailTplInParam;

/**
 * Created by leo on 2018/3/28.
 */
public interface SysEmailTplService{
    /**
     * 新增邮件告警模版
     *
     * @param inParam
     * @return
     */
    long addEmailTpl(AddEmailTplInParam inParam);

    /**
     * 修改邮件告警模版
     *
     * @param id
     * @param inParam
     * @return
     */
    void updateEmailTpl(long id, UpdateEmailTplInParam inParam);

    /**
     * 删除邮件告警模版
     *
     * @param id
     * @return
     */
    void deleteEmailTpl(long id);
}
