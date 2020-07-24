package com.jayjav.coronavirustracker.util;

import com.jayjav.coronavirustracker.dto.LocationStats;
import com.jayjav.coronavirustracker.enums.APIResponseCode;
import com.jayjav.coronavirustracker.response.ReportResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PDFGenerator {

    @Value("${com.jayjav.conatrack.report.files.path}")
    private String reportFilePath;

    @Autowired
    EmailUtil emailUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

    public ReportResponse generateJasperReport(List<LocationStats> locationStats, String reportFormat){
        LOGGER.info("Inside generateJasperReport()");
        String responseCode;
        String responseMessage;
        String destFileName = null;
        try {
            File file = ResourceUtils.getFile("classpath:covidcases.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(locationStats);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("createdBy", "Omoniyi Ilesanmi");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
            if(reportFormat.equalsIgnoreCase("html")){
                destFileName = reportFilePath + "covidcases.html";
                JasperExportManager.exportReportToHtmlFile(jasperPrint, destFileName);
            }
            if(reportFormat.equalsIgnoreCase("pdf")){
                destFileName = reportFilePath + "covidcases.pdf";
                JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
            }
            responseCode = APIResponseCode.SUCCESS.getCode();
            responseMessage = APIResponseCode.SUCCESS.getDescription();
            return new ReportResponse(responseCode, responseMessage, destFileName);
        } catch (Exception e) {
            e.printStackTrace();
            responseCode = APIResponseCode.FAILED.getCode();
            responseMessage = APIResponseCode.FAILED.getDescription();
            return new ReportResponse(responseCode, responseMessage, null);
        }
    }

}
