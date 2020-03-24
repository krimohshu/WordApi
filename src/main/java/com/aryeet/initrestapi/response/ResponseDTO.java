package com.aryeet.initrestapi.response;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseDTO {

    Map<String, Integer> responseBody = new HashMap<>();

    public ResponseDTO getResponseBody(Map<String, Integer> response) {
        this.responseBody = response;
        return this;
    }

    public Map<String, Integer> getResponse() {
        return responseBody;
    }

    public void setResponseBody(Map<String, Integer> responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "responseBody=" + responseBody +
                '}';
    }
}
