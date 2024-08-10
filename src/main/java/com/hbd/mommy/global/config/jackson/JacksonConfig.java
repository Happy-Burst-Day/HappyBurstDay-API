package com.hbd.mommy.global.config.jackson;

import java.util.List;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class JacksonConfig {

	private final List<JacksonFormatConfigurer> formatConfigurers;

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> {
			for (JacksonFormatConfigurer configurer : formatConfigurers) {
				configurer.configure(builder);
			}
		};
	}
}
