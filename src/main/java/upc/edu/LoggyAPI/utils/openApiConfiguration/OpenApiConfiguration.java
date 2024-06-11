package upc.edu.LoggyAPI.utils.openApiConfiguration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI loggyPlatformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Loggy Platform API - UPC")
                        .description("Loggy Platform application REST API documentation.")
                        .version("v4.0.0")
                        .contact(new Contact()
                                .name("Aldo Baldeon")
                                .email("u202122633@upc.edu.pe")
                                .url("https://github.com/CodAress"))
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Loggy Platform wiki Documentation")
                        .url("https://loggy-platform.wiki.github.io/docs"));
        return openApi;
    }
}
