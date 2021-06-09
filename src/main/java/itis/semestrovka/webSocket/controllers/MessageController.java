package itis.semestrovka.webSocket.controllers;

import itis.semestrovka.dto.forms.MessageForm;
import itis.semestrovka.models.Message;
import itis.semestrovka.repositories.MessageRepository;
import itis.semestrovka.security.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository repository;

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public Message sendMessage(SimpMessageHeaderAccessor headerAccessor, MessageForm form) {
        UserDetailsImpl user = (UserDetailsImpl) headerAccessor.getSessionAttributes().get("user");
        Message message = Message.builder()
                .text(form.getText())
                .username(user.getUser().getUsername())
                .build();
        repository.save(message);
        return message;
    }
}