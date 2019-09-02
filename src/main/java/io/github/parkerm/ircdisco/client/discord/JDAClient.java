package io.github.parkerm.ircdisco.client.discord;

import io.github.parkerm.ircdisco.config.DiscordConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class JDAClient implements DiscordClient {

    private AtomicBoolean running;

    public JDAClient(DiscordConfiguration config) {
        this.running = new AtomicBoolean(false);
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public Mono<Void> start() {
        return Mono.empty()
                .then()
                .doOnSuccess(v -> running.set(true));
    }

    @Override
    public Mono<Void> stop() {
        return Mono.empty()
                .then()
                .doOnSuccess(v -> running.set(false));
    }
}
