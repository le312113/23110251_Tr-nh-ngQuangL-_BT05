package com.example.BT12.Service;

import com.example.BT12.Entity.MessageEntity;
import com.example.BT12.Model.ChatMessage;
import com.example.BT12.Repository.MessageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repo;
    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public MessageEntity savePublic(ChatMessage dto) {
        MessageEntity e = new MessageEntity();
        e.setRoom(dto.getRoom() != null ? dto.getRoom() : "public");
        e.setSender(dto.getSender());
        e.setReceiver(null);
        e.setType(dto.getType() != null ? dto.getType().name() : "CHAT");
        e.setContent(dto.getContent());
        return repo.save(e);
    }

    public MessageEntity savePrivate(String receiver, ChatMessage dto) {
        MessageEntity e = new MessageEntity();
        e.setRoom(null); // hội thoại riêng, không dùng room
        e.setSender(dto.getSender());
        e.setReceiver(receiver);
        e.setType(dto.getType() != null ? dto.getType().name() : "CHAT");
        e.setContent(dto.getContent());
        return repo.save(e);
    }

    public List<MessageEntity> getRecentByRoom(String room, int limit) {
        return repo.findByRoomOrderByCreatedAtDesc(room, PageRequest.of(0, limit));
    }

    public List<MessageEntity> getPrivateHistory(String a, String b, int limit) {
        return repo.findPrivateHistory(a, b, PageRequest.of(0, limit));
    }
}
