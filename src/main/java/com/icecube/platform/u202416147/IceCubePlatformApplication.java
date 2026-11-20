package com.icecube.platform.u202416147;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Bootstrap class for the IceCube Platform application.
 *
 * <p>Initializes Spring Boot auto-configuration and JPA auditing infrastructure.</p>
 *
 * @author Carlos Cespedes
 */
@EnableJpaAuditing
@SpringBootApplication
public class IceCubePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(IceCubePlatformApplication.class, args);
    }
}
