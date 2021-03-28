package itis.semestrovka.services.interfaces;

public interface SenderService {
    void sendMessage(String subject, String email, String html);
}
