package com.example.example.model;

import lombok.Data;

@Data
public class Recomend {
    private int
            id,
            patient;

    private String
            text,
            doctor,
            created_at,
            updated_at;


}