package com.iquantex.email.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;

/**
 * Created by leo on 2018/3/28.
 */
public class FreeMarkUtil {
    private static final String DEFAULT_STRING_TPL = "default_string_tpl";
    private static final String DEFAULT_ENCODING = "utf-8";

    /**
     * 通过String格式的freemarker模版获取生成后的数据
     *
     * @param tplContent
     * @param value
     * @return
     * @throws Exception
     */
    public static String processStringTpl(String tplContent, Object value) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(DEFAULT_STRING_TPL, tplContent);
        cfg.setTemplateLoader(stringTemplateLoader);

        Template template = cfg.getTemplate(DEFAULT_STRING_TPL, DEFAULT_ENCODING);
        StringWriter writer = new StringWriter();
        template.process(value, writer);

        return writer.toString();
    }
}
