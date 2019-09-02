package io.github.parkerm.ircdisco.client.irc;

import io.github.parkerm.ircdisco.config.IrcConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PIrcBotClient implements IrcClient {

    private AtomicBoolean running;

    public PIrcBotClient(IrcConfiguration config) {
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
