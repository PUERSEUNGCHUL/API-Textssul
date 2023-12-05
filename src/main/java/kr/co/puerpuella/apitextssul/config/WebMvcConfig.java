package kr.co.puerpuella.apitextssul.config;

import kr.co.puerpuella.apitextssul.common.enums.OrderDirection;
import kr.co.puerpuella.apitextssul.common.enums.OrderType;
import kr.co.puerpuella.apitextssul.common.enums.convertor.BooleanConvertor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(Integer.class, Boolean.class, BooleanConvertor::resolve);
        registry.addConverter(String.class, OrderType.class, OrderType::resolve);
        registry.addConverter(String.class, OrderDirection.class, OrderDirection::resolve);
    }
}
