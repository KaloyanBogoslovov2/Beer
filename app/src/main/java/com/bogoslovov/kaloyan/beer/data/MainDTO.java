package com.bogoslovov.kaloyan.beer.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kaloqn on 3/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainDTO {

    private String message;
    private DataDTO data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
