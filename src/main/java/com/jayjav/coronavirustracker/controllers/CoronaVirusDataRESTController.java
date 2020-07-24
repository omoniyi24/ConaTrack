package com.jayjav.coronavirustracker.controllers;

import com.jayjav.coronavirustracker.enums.APIResponseCode;
import com.jayjav.coronavirustracker.enums.Status;
import com.jayjav.coronavirustracker.response.BaseResponse;
import com.jayjav.coronavirustracker.response.EmailResponse;
import com.jayjav.coronavirustracker.response.LocationStatsResponse;
import com.jayjav.coronavirustracker.response.ReportResponse;
import com.jayjav.coronavirustracker.services.CoronaVirusDataService;
import com.jayjav.coronavirustracker.services.EmailService;
import com.jayjav.coronavirustracker.services.ReportService;
import com.jayjav.coronavirustracker.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conatrack")
public class CoronaVirusDataRESTController {


    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/cases")
    public BaseResponse<LocationStatsResponse> getAllCases(){
        BaseResponse<LocationStatsResponse> response = new BaseResponse<>();
        response.setStatus(Status.FAIL);
        try {
            LocationStatsResponse locationStats = coronaVirusDataService.getLocationStats();
            if(locationStats.getResponseCode().equals(APIResponseCode.SUCCESS.getCode())){
                response.setStatus(Status.SUCCESS);
                response.setData(locationStats);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/report/{format}")
    public BaseResponse<ReportResponse> generateReport(@PathVariable String format ){
        BaseResponse<ReportResponse> response = new BaseResponse<>();
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setResponseCode(APIResponseCode.FAILED.getCode());
        reportResponse.setResponseMessage(APIResponseCode.FAILED.getDescription());
        reportResponse.setPathToReport(null);
        response.setStatus(Status.FAIL);
        try {
            reportResponse = reportService.exportReport(format);
            if(reportResponse.getResponseCode().equals(APIResponseCode.SUCCESS.getCode())){
                response.setStatus(Status.SUCCESS);
                response.setData(reportResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/email/{email}")
    public BaseResponse<EmailResponse> sendReportToEmail(@PathVariable String email ){
        BaseResponse<EmailResponse> response = new BaseResponse<>();
        EmailResponse emailResponse = new EmailResponse();
        emailResponse.setResponseCode(APIResponseCode.FAILED.getCode());
        emailResponse.setResponseMessage(APIResponseCode.FAILED.getDescription());
        emailResponse.setReceivingEmail(null);
        response.setStatus(Status.FAIL);
        try {
            String reportformat = "pdf";
            if(!email.isEmpty()){
                emailResponse = emailService.sendReportToEmail(reportformat,email);
                if(emailResponse.getResponseCode().equals(APIResponseCode.SUCCESS.getCode())){
                    response.setStatus(Status.SUCCESS);
                    response.setData(emailResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
