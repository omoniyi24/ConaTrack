package com.jayjav.coronavirustracker.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EmailResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String responseCode;

    private String responseMessage;

    private String receivingEmail;

    public EmailResponse(String responseCode, String responseMessage, String receivingEmail) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.receivingEmail = receivingEmail;
    }
}
