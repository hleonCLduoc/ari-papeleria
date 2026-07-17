package AriPapeleria.ms_gateway;

import AriPapeleria.ms_gateway.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)

public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}

}
