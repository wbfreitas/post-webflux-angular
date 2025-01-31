package dev.wfreitas.chatwebflux.service;


import dev.wfreitas.chatwebflux.documents.ChatItem;
import dev.wfreitas.chatwebflux.entities.MessageEvent;
import dev.wfreitas.chatwebflux.repository.ChatRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChatService {
    private final ChatRepository repository;
    private final ApplicationEventPublisher publisher;
    private final ApplicationContext applicationContext;

    public ChatService(ChatRepository repository, ApplicationEventPublisher publisher, ApplicationContext applicationContext) {
        this.repository = repository;
        this.publisher = publisher;
        this.applicationContext = applicationContext;
    }

    public Mono<ChatItem> save(ChatItem msg) {
        return repository.save(msg)
                .flatMap(p -> {
                    publisher.publishEvent(new MessageEvent(msg));
                    return Mono.just(p);
                });
    }

    public Flux<ChatItem> getMessage(int chatId) {
        return Flux.create(sink -> {
            ApplicationListener<MessageEvent> listener = event -> {
                if (event.getChatItem().getChatId() == chatId) {
                    sink.next(event.getChatItem());
                }
            };
            ApplicationEventMulticaster multicaster = (ApplicationEventMulticaster) applicationContext.getBean("applicationEventMulticaster");
            multicaster.addApplicationListener(listener);
            sink.onDispose(() -> multicaster.removeApplicationListener(listener));
        });
    }
}
