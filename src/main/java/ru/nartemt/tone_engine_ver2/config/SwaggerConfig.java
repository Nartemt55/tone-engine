package ru.nartemt.tone_engine_ver2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private final String SERVER_URL = "http://localhost:8080";

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(new Server().url(SERVER_URL))
                )
                .info(
                        new Info().title("Tone engine API")
                );
    }
}
