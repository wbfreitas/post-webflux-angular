package dev.wfreitas.chatwebflux.repository;

import dev.wfreitas.chatwebflux.documents.ChatItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ChatRepository extends ReactiveMongoRepository<ChatItem, String> {
}
