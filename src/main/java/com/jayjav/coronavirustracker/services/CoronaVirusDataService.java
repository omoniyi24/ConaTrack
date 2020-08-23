package com.jayjav.coronavirustracker.services;

import java.awt.print.Pageable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import com.jayjav.coronavirustracker.dto.LocationStats;
import com.jayjav.coronavirustracker.enums.APIResponseCode;
import com.jayjav.coronavirustracker.response.LocationStatsResponse;
import com.jayjav.coronavirustracker.util.RequestCasesOnline;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created By: Ilesanmi Omoniyi
 * Date: 3/5/2020 8:12 AM
 */

@Service
public class CoronaVirusDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoronaVirusDataService.class);

    @Autowired
    private RequestCasesOnline requestCasesOnline;

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    private List<LocationStats> allStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() {
        LOGGER.info("[+] Code in fetchVirusData()");
        try {
            this.allStats = getLocationStats().getLocationStatsList();
            LOGGER.info("[+] Executed fetchVirusData() with no Error");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(String.format("[-] Executed fetchVirusData() with Error %s", e.getMessage()));
        }
    }

    public LocationStatsResponse getLocationStats(){
        LOGGER.info("[+] Code in getLocationStats()");
        List<LocationStats> newStats = new ArrayList<>();
        String responseCode;
        String responseMessage;
        int count = 0;
        try {
            StringReader reader = requestCasesOnline.callThirdPartyForData();
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                count++;
                LocationStats locationStats = new LocationStats();
                locationStats.setId(count);
                locationStats.setState(record.get("Province/State"));
                locationStats.setCountry(record.get("Country/Region"));
                int latestCases = Integer.parseInt(record.get(record.size()-1));
                int prevDayCases = Integer.parseInt(record.get(record.size()-2));
                locationStats.setLatestTotalCases(latestCases);
                locationStats.setDiffFromPrevDay(latestCases - prevDayCases);
                newStats.add(locationStats);
                newStats = getPage(newStats, 1, 7);

            }
            LOGGER.info("[+] Executed getLocationStats() with no Error");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(String.format("[-] Executed getLocationStats() with Error %s", e.getMessage()));
        }
        if (!newStats.isEmpty()){
            responseCode = APIResponseCode.SUCCESS.getCode();
            responseMessage = APIResponseCode.SUCCESS.getDescription();
            LOGGER.info("[+] Leaving getLocationStats() with {}",responseCode);
            return new LocationStatsResponse(responseCode, responseMessage, newStats);
        }else {
            responseCode = APIResponseCode.FAILED.getCode();
            responseMessage = APIResponseCode.FAILED.getDescription();
            LOGGER.info("[-] Leaving getLocationStats() with {}",responseCode);
            return new LocationStatsResponse(responseCode, responseMessage, null);
        }
    }

    public int getTotalReportedCases() {
        LOGGER.info("[+] Code in getTotalReportedCases()");
        return allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
    }

    public int getTotalNewCases() {
        LOGGER.info("[+] Code in getTotalNewCases()");
        return allStats.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();
    }

    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }
        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() < fromIndex){
            return Collections.emptyList();
        }
        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

}
