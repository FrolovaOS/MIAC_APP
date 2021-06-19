package com.example.example.model;

import com.example.example.model.MeasurementAdd;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Measurement extends MeasurementAdd {

    int id;

    @SerializedName("created_at")
    private String dataCreate;

    @SerializedName("updated_at")
    private String dataUpdate;

}
