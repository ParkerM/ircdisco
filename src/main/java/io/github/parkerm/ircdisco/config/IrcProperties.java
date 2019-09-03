package io.github.parkerm.ircdisco.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("irc")
@Data
@Getter
@Setter
public class IrcProperties {
    private String nick;
    private String login;
    private boolean autoNickChange;
    private String autoJoinChannel;
    private String serverUrl;
    private int serverPort;
    private boolean sslEnabled;
}
