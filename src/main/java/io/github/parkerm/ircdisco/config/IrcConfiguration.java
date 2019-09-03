package io.github.parkerm.ircdisco.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(IrcProperties.class)
public class IrcConfiguration {

    private IrcProperties properties;

    public IrcConfiguration(IrcProperties properties) {
        this.properties = properties;
    }

    public String getNick() {
        return properties.getNick();
    }

    public String getLogin() {
        return properties.getLogin();
    }

    public boolean getAutoNickChange() {
        return properties.isAutoNickChange();
    }

    public String getAutoJoinChannel() {
        return properties.getAutoJoinChannel();
    }

    public String getServerUrl() {
        return properties.getServerUrl();
    }

    public int getServerPort() {
        return properties.getServerPort();
    }

    public boolean getSslEnabled() {
        return properties.isSslEnabled();
    }
}
