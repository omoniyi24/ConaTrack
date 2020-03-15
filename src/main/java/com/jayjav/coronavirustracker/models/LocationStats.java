package com.jayjav.coronavirustracker.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;

    public LocationStats(String state, String country, int latestTotalCases, int diffFromPrevDay) {
        this.state = state;
        this.country = country;
        this.latestTotalCases = latestTotalCases;
        this.diffFromPrevDay = diffFromPrevDay;
    }

    public LocationStats() {
    }
}

