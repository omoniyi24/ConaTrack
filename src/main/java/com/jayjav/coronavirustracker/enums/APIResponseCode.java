package com.jayjav.coronavirustracker.enums;

public enum APIResponseCode {
    SUCCESS("00", "Successful"), FAILED("01", "failed"),
    FILE_NOT_SUPPORTED("03", "File type not supported");


    private String code;

    private String description;


    APIResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }


}
