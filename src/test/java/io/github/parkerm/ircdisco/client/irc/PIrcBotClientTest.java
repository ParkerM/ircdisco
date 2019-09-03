package io.github.parkerm.ircdisco.client.irc;

import io.github.parkerm.ircdisco.config.IrcConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PIrcBotClientTest {

    @Mock
    private IrcConfiguration config;

    private IrcClient pIrcClient;

    @BeforeEach
    void setUp() {
        pIrcClient = new PIrcBotClient(config);
    }

    @Test
    void isRunning() {
        assertThat(pIrcClient.isRunning()).isFalse();

        pIrcClient.start();
        assertThat(pIrcClient.isRunning()).isTrue();

        pIrcClient.stop();
        assertThat(pIrcClient.isRunning()).isFalse();
    }
}
