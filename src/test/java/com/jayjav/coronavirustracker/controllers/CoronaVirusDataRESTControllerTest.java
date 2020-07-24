package com.jayjav.coronavirustracker.controllers;

import com.jayjav.coronavirustracker.enums.APIResponseCode;
import com.jayjav.coronavirustracker.enums.Status;
import com.jayjav.coronavirustracker.response.BaseResponse;
import com.jayjav.coronavirustracker.response.LocationStatsResponse;
import com.jayjav.coronavirustracker.response.ReportResponse;
import com.jayjav.coronavirustracker.services.CoronaVirusDataService;
import com.jayjav.coronavirustracker.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CoronaVirusDataRESTControllerTest {


    @Mock
    private CoronaVirusDataService coronaVirusDataService;

    @Autowired
    @InjectMocks
    private CoronaVirusDataRESTController coronaVirusDataRESTController;

    @Mock
    private ReportService reportService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test All Cases Found")
    public void getAllCasesShouldReturnSuccessResponse(){
        LocationStatsResponse response = new LocationStatsResponse();
        response.setResponseCode(APIResponseCode.SUCCESS.getCode());
        response.setResponseMessage(APIResponseCode.SUCCESS.getDescription());
        when(coronaVirusDataService.getLocationStats()).thenReturn(response);
        BaseResponse<LocationStatsResponse> allCases = coronaVirusDataRESTController.getAllCases();
        assertEquals(allCases.getStatus(), Status.SUCCESS);
        verify(coronaVirusDataService).getLocationStats();
    }

    @Test
    public void getAllCasesShouldReturnAFailedResponse(){
        LocationStatsResponse response = new LocationStatsResponse();
        response.setResponseCode(APIResponseCode.FAILED.getCode());
        response.setResponseMessage(APIResponseCode.FAILED.getDescription());
        when(coronaVirusDataService.getLocationStats()).thenReturn(response);
        BaseResponse<LocationStatsResponse> allCases = coronaVirusDataRESTController.getAllCases();
        assertEquals(allCases.getStatus(), Status.FAIL);
        verify(coronaVirusDataService).getLocationStats();
    }

    @Test
    public void generateReportShouldReturnSuccessResponse(){
        ReportResponse response = new ReportResponse();
        response.setResponseCode(APIResponseCode.SUCCESS.getCode());
        response.setResponseMessage(APIResponseCode.SUCCESS.getDescription());
        String reportFormat = "pdf";
        when(reportService.exportReport(reportFormat)).thenReturn(response);
        BaseResponse<ReportResponse> allCases = coronaVirusDataRESTController.generateReport(reportFormat);
        assertEquals(allCases.getStatus(), Status.SUCCESS);
        verify(reportService).exportReport(reportFormat);
    }

    @Test
    public void generateReportShouldReturnAFailedResponse(){
        ReportResponse response = new ReportResponse();
        response.setResponseCode(APIResponseCode.FAILED.getCode());
        response.setResponseMessage(APIResponseCode.FAILED.getDescription());
        String reportFormat = "pdf";
        when(reportService.exportReport(reportFormat)).thenReturn(response);
        BaseResponse<ReportResponse> allCases = coronaVirusDataRESTController.generateReport(reportFormat);
        assertEquals(allCases.getStatus(), Status.FAIL);
        verify(reportService).exportReport(reportFormat);
    }


}
