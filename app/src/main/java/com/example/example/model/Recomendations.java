package com.example.example.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Recomendations extends UserAbstract{

    @SerializedName("recomendation")
    private List<Recomend> recomendations;

}
