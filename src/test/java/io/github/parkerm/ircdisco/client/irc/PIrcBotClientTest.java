package io.github.parkerm.ircdisco.client.irc;

import io.github.parkerm.ircdisco.config.IrcConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

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

        StepVerifier.create(pIrcClient.start()).verifyComplete();
        assertThat(pIrcClient.isRunning()).isTrue();

        StepVerifier.create(pIrcClient.stop()).verifyComplete();
        assertThat(pIrcClient.isRunning()).isFalse();
    }
}
