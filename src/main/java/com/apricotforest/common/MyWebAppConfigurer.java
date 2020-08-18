package com.apricotforest.common;

import com.apricotforest.common.factory.*;
import org.springframework.context.annotation.*;
import org.springframework.format.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author smallAttr
 * @since 2020-08-18 13:31
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToBaseEnumConverterFactory());
    }
}
