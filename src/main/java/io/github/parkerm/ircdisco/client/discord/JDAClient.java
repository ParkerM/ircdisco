package io.github.parkerm.ircdisco.client.discord;

import club.minnced.jda.reactor.ReactiveEventManager;
import io.github.parkerm.ircdisco.config.DiscordConfiguration;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.security.auth.login.LoginException;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class JDAClient implements DiscordClient {

    private AtomicBoolean running;
    private JDA jda;
    private ReactiveEventManager manager;

    public JDAClient(DiscordConfiguration config) {
        running = new AtomicBoolean(false);
        manager = new ReactiveEventManager();
        manager.on(ReadyEvent.class)
                .next()
                .subscribe((a) -> log.info("JDA is ready! Total Guild count: {}", a.getGuildTotalCount()));
        manager.on(MessageReceivedEvent.class)
                .map(MessageReceivedEvent::getMessage)
                .map(Message::getChannel)
                .map(mc -> mc.sendMessage("It me!"))
                .flatMap(Mono::just)
                .subscribe();
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public void start() {
        try {
            jda = new JDABuilder("token")
                    .setEventManager(manager)
                    .build();
            running.set(true);
        } catch (LoginException e) {
            log.error("Error starting JDAClient.", e);
            running.set(false);
        }
    }

    @Override
    public void stop() {
        jda.shutdown();
        running.set(false);
    }
}
