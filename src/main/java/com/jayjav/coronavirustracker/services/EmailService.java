package com.jayjav.coronavirustracker.services;

import com.jayjav.coronavirustracker.dto.LocationStats;
import com.jayjav.coronavirustracker.enums.APIResponseCode;
import com.jayjav.coronavirustracker.response.EmailResponse;
import com.jayjav.coronavirustracker.response.ReportResponse;
import com.jayjav.coronavirustracker.util.EmailUtil;
import com.jayjav.coronavirustracker.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    public EmailResponse sendReportToEmail(String reportFormat, String toAddress){
        List<LocationStats> locationStats = coronaVirusDataService.getLocationStats().getLocationStatsList();
        String responseCode = APIResponseCode.FAILED.getCode();
        String responseMessage = APIResponseCode.FAILED.getDescription();
        if(reportFormat.equalsIgnoreCase("html") || reportFormat.equalsIgnoreCase("pdf")){
            if(!locationStats.isEmpty()){
                ReportResponse reportResponse = pdfGenerator.generateJasperReport(locationStats, reportFormat);
                if(reportResponse.getResponseCode() == APIResponseCode.SUCCESS.getCode()){
                    boolean mailSent = emailUtil.sendReport(toAddress, reportResponse.getPathToReport());
                    if(mailSent){
                        return new EmailResponse(responseCode, responseMessage, null);
                    }
                }
            }else {
                responseCode = APIResponseCode.FAILED.getCode();
                responseMessage = APIResponseCode.FAILED.getDescription();
                return new EmailResponse(responseCode, responseMessage, null);
            }
        }else {
            responseCode = APIResponseCode.FILE_NOT_SUPPORTED.getCode();
            responseMessage = APIResponseCode.FILE_NOT_SUPPORTED.getDescription();
            return new EmailResponse(responseCode, responseMessage, null);
        }
        return new EmailResponse(responseCode, responseMessage, null);
    }
}
