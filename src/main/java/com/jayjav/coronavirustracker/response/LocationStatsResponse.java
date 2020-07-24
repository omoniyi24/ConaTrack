package com.jayjav.coronavirustracker.response;


import com.jayjav.coronavirustracker.dto.LocationStats;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class LocationStatsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String responseCode;

    private String responseMessage;

    private List<LocationStats> locationStatsList;

    public LocationStatsResponse(String responseCode, String responseMessage, List<LocationStats> locationStatsList) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.locationStatsList = locationStatsList;
    }
}
