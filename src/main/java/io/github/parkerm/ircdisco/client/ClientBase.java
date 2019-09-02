package io.github.parkerm.ircdisco.client;

import reactor.core.publisher.Mono;

public interface ClientBase {

    boolean isRunning();

    Mono<Void> start();

    Mono<Void> stop();
}
