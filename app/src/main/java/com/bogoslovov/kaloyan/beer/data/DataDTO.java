package com.bogoslovov.kaloyan.beer.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kaloqn on 3/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDTO {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
