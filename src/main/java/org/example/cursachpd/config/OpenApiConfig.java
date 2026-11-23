package org.example.cursachpd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация OpenAPI/Swagger документации.
 * Настраивает метаданные API: название, описание, версию и контактную информацию.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Создаёт и настраивает OpenAPI спецификацию.
     *
     * @return настроенный OpenAPI объект
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CursachPD Recommendations API")
                        .description("REST API for the recommendations engine with static and dynamic rules")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Course Project Team")));
    }
}
