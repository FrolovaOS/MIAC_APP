package com.example.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementAdd {

    private int
            pressure_high,
            pressure_low,
            pulse,
            saturation,
            patient;

    private String type;

}
