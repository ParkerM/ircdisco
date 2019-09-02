package io.github.parkerm.ircdisco.client.discord;

import io.github.parkerm.ircdisco.config.DiscordConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JDAClientTest {

    @Mock
    private DiscordConfiguration config;

    private DiscordClient jdaClient;

    @BeforeEach
    void setUp() {
        jdaClient = new JDAClient(config);
    }

    @Test
    void isRunning() {
        assertThat(jdaClient.isRunning()).isFalse();

        StepVerifier.create(jdaClient.start()).verifyComplete();
        assertThat(jdaClient.isRunning()).isTrue();

        StepVerifier.create(jdaClient.stop()).verifyComplete();
        assertThat(jdaClient.isRunning()).isFalse();
    }
}
