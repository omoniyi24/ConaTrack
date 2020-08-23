package com.jayjav.coronavirustracker.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Data
@ToString
public class LocationStats implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;

    public LocationStats(int id, String state, String country, int latestTotalCases, int diffFromPrevDay) {
        this.id = id;
        this.state = state;
        this.country = country;
        this.latestTotalCases = latestTotalCases;
        this.diffFromPrevDay = diffFromPrevDay;
    }

    public LocationStats() {
    }
}

