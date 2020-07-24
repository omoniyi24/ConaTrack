package com.jayjav.coronavirustracker.services;

import com.jayjav.coronavirustracker.enums.APIResponseCode;
import com.jayjav.coronavirustracker.dto.LocationStats;
import com.jayjav.coronavirustracker.response.ReportResponse;
import com.jayjav.coronavirustracker.util.EmailUtil;
import com.jayjav.coronavirustracker.util.PDFGenerator;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class ReportService {

    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    public ReportResponse exportReport(String reportFormat){
        List<LocationStats> locationStats = coronaVirusDataService.getLocationStats().getLocationStatsList();
        String responseCode;
        String responseMessage;
        if(reportFormat.equalsIgnoreCase("html") || reportFormat.equalsIgnoreCase("pdf")){
            if(!locationStats.isEmpty()){
                return pdfGenerator.generateJasperReport(locationStats, reportFormat);
            }else {
                responseCode = APIResponseCode.FAILED.getCode();
                responseMessage = APIResponseCode.FAILED.getDescription();
                return new ReportResponse(responseCode, responseMessage, null);
            }
        }else {
            responseCode = APIResponseCode.FILE_NOT_SUPPORTED.getCode();
            responseMessage = APIResponseCode.FILE_NOT_SUPPORTED.getDescription();
            return new ReportResponse(responseCode, responseMessage, null);
        }
    }
}
