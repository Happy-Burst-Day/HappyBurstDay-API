package com.hbd.mommy.global.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hbd.mommy.global.auth.jwt.JwtProvider;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecurityScheme(
	name = JwtProvider.AUTHORIZATION,
	type = SecuritySchemeType.HTTP,
	scheme = "Bearer",
	bearerFormat = "JWT",
	in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.components(new Components())
			.info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
			.title("Junction HappyBurstDay API")
			.version("1.0.0");
	}
}
