package com.mruruc.fu.search.service.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.typesense.api.Client;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "typesense")
@Data
@Slf4j
public class TypesenseConfig {
    private String host;
    private String port;
    private String protocol;
    private String apiKey;
    private Duration connectionTimeout;

    @Bean
    public Client typesenseClient() {
        log.info("Initializing Typesense client with host: {}, port: {}, protocol: {}, apiKey: {}, connectionTimeout: {}",
                host, port, protocol,
                apiKey == null ? "*****************" : "null",
                connectionTimeout);
        var nodes = List.of(new Node(protocol, host, port));
        var config = new org.typesense.api.Configuration(nodes, connectionTimeout, apiKey);
        return new Client(config);
    }


}
