package ru.nartemt.tone_engine_ver2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, EquipmentType.class,
                source -> EquipmentType.valueOf(source.toUpperCase()));
    }
}
