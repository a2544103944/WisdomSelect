package com.cjj.common.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * @author 陈建军
 * @version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     *消息转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //处理JSON数据
        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
        //日期格式化
        ObjectMapper objectMapper = converter.getObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //忽略null值
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //后台Long值传递给前端丢失精度问题
        SimpleModule simpleModule=new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class,ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        converter.setObjectMapper(objectMapper);
        converters.add(0,converter);
    }
}
