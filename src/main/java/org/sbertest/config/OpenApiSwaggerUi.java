package org.sbertest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiSwaggerUi {

    @Bean
    public OpenAPI openAPIDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8888/api/users");
        localhostServer.setDescription("User request");

        Contact contact = new Contact();
        contact.setName("Aleks Zhmaylov");
        contact.setEmail("mozday@yandex.ru");


        Info info = new Info()
                .title("Sberbank Test  API")
                .version("1.0")
                .contact(contact)
                .description("API Users and roles");
        return new OpenAPI().info(info).servers(List.of(localhostServer));
    }
}
