package dev.wfreitas.chatwebflux.controller;

import dev.wfreitas.chatwebflux.documents.ChatItem;
import dev.wfreitas.chatwebflux.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService service;
    public ChatController(ChatService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ChatItem> save(@RequestBody ChatItem msg) {
        return service.save(msg);
    }
    @GetMapping(value = "/{chatId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatItem> chat(@PathVariable int chatId) {
        return service.getMessage(chatId);
    }
}
