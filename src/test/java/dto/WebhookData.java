package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebhookData {
    private String webhookName;
    private String payloadURL;
    private String method;
    private String contentType;
    private String headers;
    private String payload;
    private String secret;
    private String value;
}

