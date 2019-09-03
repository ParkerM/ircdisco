package io.github.parkerm.ircdisco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class IrcdiscoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IrcdiscoApplication.class, args);
    }

}
