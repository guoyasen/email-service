package com.iquantex.email.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iquantex.email.dao.mapper.SysEmailTplMapper;
import com.iquantex.email.dao.model.SysEmailTpl;
import com.iquantex.email.exception.CommonException;
import com.iquantex.email.exception.EmailException;
import com.iquantex.email.param.AddEmailTplInParam;
import com.iquantex.email.param.UpdateEmailTplInParam;
import com.iquantex.email.service.SysEmailTplService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by leo on 2018/3/28.
 */
@Service
public class SysEmailTplServiceImpl implements SysEmailTplService {
    @Autowired
    private SysEmailTplMapper emailTplDao;

    @Override
    public long addEmailTpl(AddEmailTplInParam inParam) {
        SysEmailTpl emailTpl = handleAddEmailAlarm(inParam);
        // 判断编码是否唯一
        checkAlarmCode(emailTpl.getCode());
        emailTplDao.insert(emailTpl);

        return emailTpl.getId();
    }

    @Override
    public void updateEmailTpl(long id, UpdateEmailTplInParam inParam) {
        SysEmailTpl emailTpl = emailTplDao.selectById(id);
        if (null == emailTpl) {
            throw new EmailException("邮件模版【" + id + "id}】不存在");
        }

        SysEmailTpl updateEmailAlarm = handleUpdateEmailAlarm(inParam);
        // 如果编码有修改，需要判断修改后的编码是否唯一
        if (!Objects.equals(emailTpl.getCode(), updateEmailAlarm.getCode())) {
            checkAlarmCode(updateEmailAlarm.getCode());
        }
        updateEmailAlarm.setId(id);
        updateEmailAlarm.setCreateTime(emailTpl.getCreateTime());
        updateEmailAlarm.setCreator(emailTpl.getCreator());

        emailTplDao.updateById(updateEmailAlarm);

    }

    /**
     * 校验邮件编码是否唯一
     *
     * @param code
     */
    private void checkAlarmCode(String code) {
        QueryWrapper<SysEmailTpl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SysEmailTpl.CODE, code);
        int count = emailTplDao.selectCount(queryWrapper);
        if (count > 0) {
            throw new EmailException("邮件编码【"+ code + "】已存在");
        }
    }

    /**
     * 新增模板，处理请求入参
     *
     * @param inParam
     * @return
     */
    private SysEmailTpl handleAddEmailAlarm(AddEmailTplInParam inParam) {
        SysEmailTpl emailTpl = new SysEmailTpl();
        BeanUtils.copyProperties(inParam, emailTpl);
        emailTpl.setReceiverRole(convertList2String(inParam.getReceiverRole()));
        emailTpl.setCcRole(convertList2String(inParam.getCcRole()));
        emailTpl.setReceiverUser(convertList2String(inParam.getReceiverUser()));
        emailTpl.setCcUser(convertList2String(inParam.getCcUser()));
        emailTpl.setBlackUser(convertList2String(inParam.getBlackUser()));
        emailTpl.setBlackRole(convertList2String(inParam.getBlackRole()));
        emailTpl.setFundReceiverRole(convertList2String(inParam.getFundReceiverRole()));
        emailTpl.setFundCcRole(convertList2String(inParam.getFundCcRole()));
        emailTpl.setFundCodes(convertList2String(inParam.getFundCodes()));
//        emailTpl.setCreator(SessionData.getUserId());
        emailTpl.setCreateTime(new Date());

        return emailTpl;
    }

    /**
     * 更新模板，处理请求入参
     *
     * @param inParam
     * @return
     */
    private SysEmailTpl handleUpdateEmailAlarm(UpdateEmailTplInParam inParam) {
        SysEmailTpl emailTpl = new SysEmailTpl();
        BeanUtils.copyProperties(inParam, emailTpl);
        emailTpl.setReceiverRole(convertList2String(inParam.getReceiverRole()));
        emailTpl.setCcRole(convertList2String(inParam.getCcRole()));
        emailTpl.setReceiverUser(convertList2String(inParam.getReceiverUser()));
        emailTpl.setCcUser(convertList2String(inParam.getCcUser()));
        emailTpl.setBlackUser(convertList2String(inParam.getBlackUser()));
        emailTpl.setBlackRole(convertList2String(inParam.getBlackRole()));
        emailTpl.setFundReceiverRole(convertList2String(inParam.getFundReceiverRole()));
        emailTpl.setFundCcRole(convertList2String(inParam.getFundCcRole()));
        emailTpl.setFundCodes(convertList2String(inParam.getFundCodes()));
//        emailTpl.setModifier(SessionData.getUserId());
        emailTpl.setModifyTime(new Date());

        return emailTpl;
    }

    private static final String DELIMITER = ",";
    /**
     * 如果list不为空，则将list转化为数组形式
     *
     * @param strings
     * @return
     */
    private String convertList2String(List strings) {
        return StringUtils.join(strings, DELIMITER);
    }

    @Override
    public void deleteEmailTpl(long id) {
        emailTplDao.deleteById(id);
    }
}
