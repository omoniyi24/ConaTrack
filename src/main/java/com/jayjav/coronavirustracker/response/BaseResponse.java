package com.jayjav.coronavirustracker.response;

import com.jayjav.coronavirustracker.enums.Status;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private  T data;

    private Status status;
}
