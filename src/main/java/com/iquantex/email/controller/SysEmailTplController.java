package com.iquantex.email.controller;

import com.iquantex.email.controller.dto.EmailResponseDto;
import com.iquantex.email.param.AddEmailTplInParam;
import com.iquantex.email.param.UpdateEmailTplInParam;
import com.iquantex.email.service.SysEmailTplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by leo on 2018/3/28.
 */
@RestController
@RequestMapping("/api/v1/email_tpls")
public class SysEmailTplController{
    @Autowired
    private SysEmailTplService service;

    /**
     * 新增邮件告警模版
     *
     * @param inParam
     * @return
     */
    @PostMapping
    public EmailResponseDto addEmailTpl(@RequestBody @Valid AddEmailTplInParam inParam) {
        service.addEmailTpl(inParam);
//        return successReturn().setData("id", service.addEmailTpl(inParam));
        return new EmailResponseDto();
    }

    /**
     * 修改邮件告警模版
     *
     * @param id
     * @param inParam
     * @return
     */
    @PutMapping("/{id}")
    public EmailResponseDto updateEmailTpl(@PathVariable long id, @RequestBody @Valid UpdateEmailTplInParam inParam) {
        service.updateEmailTpl(id, inParam);
//        return successReturn();
        return new EmailResponseDto();
    }

    /**
     * 删除邮件告警模版
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public EmailResponseDto deleteEmailTpl(@PathVariable long id) {
        service.deleteEmailTpl(id);
//        return successReturn();
        return new EmailResponseDto();
    }
}
