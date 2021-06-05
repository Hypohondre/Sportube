package itis.semestrovka.services.implementations;

import itis.semestrovka.services.interfaces.SmsConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SmsConfirmServiceImpl implements SmsConfirmService {
    private final RestTemplate restTemplate;

    @Value("${sms.base.url}")
    private String baseUrl;
    @Value("${sms.send.path}")
    private String sendSmsPath;
    @Value("${api.key}")
    private String key;
    @Value("${sms.api.parameter}")
    private String apiParameterValue;
    @Value("${sms.receiver.key_start}")
    private String receiverKeyStart;
    @Value("${sms.receiver.key_end}")
    private String receiverKeyEnd;
    @Value("${sms.json.enabled.key}")
    private String jsonEnabledKey;

    @Override
    public String sendSms(String phone, String text) {
        String smsUrl = baseUrl
                + sendSmsPath
                + apiParameterValue + key
                + receiverKeyStart + phone + receiverKeyEnd
                + "=" + extractText(text) + jsonEnabledKey;
        return restTemplate.getForObject(smsUrl, String.class);
    }

    private String extractText(String text) {
        String[] result = text.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(result[0]);
        for (int i = 1; i < result.length ; i++) {
            sb.append("+").append(result[i]);
        }
        return sb.toString();
    }
}
