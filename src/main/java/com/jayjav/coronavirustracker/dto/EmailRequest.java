package com.jayjav.coronavirustracker.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class EmailRequest implements Serializable {

    private String emailAddr;
}
