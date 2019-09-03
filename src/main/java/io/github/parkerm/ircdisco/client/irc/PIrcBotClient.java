package io.github.parkerm.ircdisco.client.irc;

import io.github.parkerm.ircdisco.config.IrcConfiguration;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.pircbotx.exception.IrcException;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PIrcBotClient implements IrcClient {

    private Scheduler scheduler;
    private AtomicBoolean running;
    private Disposable runnerSubscription;
    private PircBotX bot;

    public PIrcBotClient(IrcConfiguration ircConfig) {
        this.running = new AtomicBoolean(false);
        Configuration.Builder botConfigBuilder = new Configuration.Builder()
                .setName(ircConfig.getNick())
                .setLogin(ircConfig.getLogin())
                .setAutoNickChange(ircConfig.getAutoNickChange())
                .addAutoJoinChannel(ircConfig.getAutoJoinChannel());
        botConfigBuilder.addServer(ircConfig.getServerUrl(), ircConfig.getServerPort());
        if (ircConfig.getSslEnabled()) {
            botConfigBuilder.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates());
        }

        bot = new PircBotX(botConfigBuilder.buildConfiguration());
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public void start() {
        scheduler = Schedulers.newElastic("irc-client");
        Mono blockingWrapper = Mono.fromRunnable(() -> {
            try {
                bot.startBot();
            } catch (IOException | IrcException e) {
                running.set(false);
            }
        });
        blockingWrapper = blockingWrapper.subscribeOn(scheduler);
        runnerSubscription = blockingWrapper.subscribe();
        running.set(true);
    }

    @Override
    public void stop() {
        bot.sendIRC().quitServer("Shutting down...");
        runnerSubscription.dispose();
        scheduler.dispose();
        running.set(false);
    }
}
