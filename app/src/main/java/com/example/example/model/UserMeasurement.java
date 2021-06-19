package com.example.example.model;

import com.example.example.rubish.Measurement;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class UserMeasurement {

    private String username,
            email,
            first_name,
            last_name;

    @SerializedName("measurement")
    private List<Measurement> measurements;

}
