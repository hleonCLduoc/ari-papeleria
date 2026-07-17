package AriPapeleria.ms_gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(String secret, Long expirationMinutes) {
}

