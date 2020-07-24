package com.jayjav.coronavirustracker.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ReportResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String responseCode;

    private String responseMessage;

    private String pathToReport;

    public ReportResponse(String responseCode, String responseMessage, String pathToReport) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.pathToReport = pathToReport;
    }

}
