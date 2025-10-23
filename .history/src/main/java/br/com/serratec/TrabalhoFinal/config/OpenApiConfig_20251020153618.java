package br.com.serratec.commerce.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    private final String devUri;
    private final String prodUri;

    public OpenApiConfig(
            @Value("${dominio.openapi.dev-uri:http://localhost:8080}") String devUri,
            @Value("${dominio.openapi.prod-uri:https://api.seudominio.com.br}") String prodUri) {
        this.devUri = devUri;
        this.prodUri = prodUri;
    }

    @Bean
    OpenAPI myOpenApi() {
        
        Server devServer = new Server();
        devServer.setUrl(devUri);
        devServer.setDescription("URL do Servidor de Desenvolvimento");

        Server prodServer = new Server();
        prodServer.setUrl(prodUri); 
        prodServer.setDescription("URL do Servidor de Produção");

        Contact contact = new Contact();
        contact.setEmail("jp.manerorc@gmail.com");
        contact.setName("Joao Dias");
        contact.setUrl("www.meudominio.com");

        Info info = new Info()
                .title("API de Gestão de Vendas - Serratec")
                .version("1.0")
                .description("Documentação da API RESTful para o sistema de vendas, ")
                .contact(new Contact()
                        .name("João Dias")
                        .email("jp.manerorc@gmail.com")
                        .url("www.meudominio.com"))
                .termsOfService("Api usada para testes e demonstração do curso.");

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}