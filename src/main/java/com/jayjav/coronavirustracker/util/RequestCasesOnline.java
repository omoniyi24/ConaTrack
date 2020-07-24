package com.jayjav.coronavirustracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class RequestCasesOnline {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestCasesOnline.class);

    private static final String VIRUSDATAURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public StringReader callThirdPartyForData() {
        LOGGER.info("[+] Code in callThirdPartyForData()");
        StringReader reader = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(VIRUSDATAURL))
                    .build();

            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            reader = new StringReader(httpResponse.body());
            LOGGER.info("[+] Executed callThirdPartyForData() with no Error and Reader is {}", reader);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info(String.format("[-] Executed callThirdPartyForData() with Error %s", e.getMessage()));
        }
        return reader;
    }
}
