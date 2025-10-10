package com.example.BT12.Controller;
import com.example.BT12.Model.ChatMessage;
import com.example.BT12.Service.MessageService;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    @GetMapping({"/", "/chat"})
    public String chat() {
        return "chat";
    }
    public ChatController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/chat.sendPublic")
    public void sendPublic(@Payload ChatMessage message) {
        // Lưu DB
        messageService.savePublic(message);
        // Phát tới phòng public
        messagingTemplate.convertAndSend("/topic/public", message);
    }

    @MessageMapping("/chat.sendPrivate")
    public void sendPrivate(@Header("receiver") String receiver,
                            @Payload ChatMessage message) {
        // Lưu DB
        messageService.savePrivate(receiver, message);
        // Gửi kênh riêng
        messagingTemplate.convertAndSendToUser(receiver, "/queue/support", message);
    }

    @MessageMapping("/chat.join")
    public void join(@Payload ChatMessage message) {
        message.setType(ChatMessage.MessageType.JOIN);
        messageService.savePublic(message);
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}
