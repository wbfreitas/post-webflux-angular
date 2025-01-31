package dev.wfreitas.chatwebflux.entities;

import dev.wfreitas.chatwebflux.documents.ChatItem;
import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

    private ChatItem chatItem;
    public MessageEvent(ChatItem chatItem) {
        super(chatItem);
        this.chatItem = chatItem;
    }

    public ChatItem getChatItem() {
        return chatItem;
    }

    public void setChatItem(ChatItem chatItem) {
        this.chatItem = chatItem;
    }
}
