package io.github.parkerm.ircdisco.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(IrcProperties.class)
public class IrcConfiguration {
}
