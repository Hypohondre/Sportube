package itis.semestrovka.services.interfaces;

public interface SmsConfirmService {

    String sendSms(String phone, String text);

}
