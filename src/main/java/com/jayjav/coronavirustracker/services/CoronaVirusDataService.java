package com.jayjav.coronavirustracker.services;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.jayjav.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created By: Ilesanmi Omoniyi
 * Date: 3/5/2020 8:12 AM
 */

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    private List<LocationStats> allStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() {
        try {
            List<LocationStats> newStats = new ArrayList<>();
            StringReader reader = callThirdPartyForData();
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
				LocationStats locationStats = new LocationStats();
				locationStats.setState(record.get("Province/State"));
				locationStats.setCountry("Country/Region");
//				System.out.println("?>>>>>>>>>>>>>>>>>>>>");
//				System.out.println(record.get(record.size()-1));
				int latestCases = Integer.parseInt(record.get(record.size()-1));
				int prevDayCases = Integer.parseInt(record.get(record.size()-2));
				locationStats.setLatestTotalCases(latestCases);
				locationStats.setDiffFromPrevDay(latestCases - prevDayCases);
                newStats.add(locationStats);
            }
            this.allStats = newStats;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public StringReader  callThirdPartyForData() {
        StringReader reader = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(VIRUS_DATA_URL))
                    .build();

            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            reader = new StringReader(httpResponse.body());

        }catch (Exception e){
            e.printStackTrace();
        }
        return reader;
    }

    public int getTotalReportedCases() {
        return allStats.stream().mapToInt(stats -> stats.getLatestTotalCases()).sum();
    }

    public int getTotalNewCases() {
        return allStats.stream().mapToInt(stats -> stats.getDiffFromPrevDay()).sum();
    }


}
