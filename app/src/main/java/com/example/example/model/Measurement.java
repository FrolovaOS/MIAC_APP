package com.example.example.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

//    @SerializedName("TODO УЗнать у Ильи или глянь сам")
    private Integer id;

    private String
            timestamp,
            lowPressure,
            highPressure,
            pulse,
            saturation;

    public Measurement(String timestamp, String lowPressure, String highPressure, String pulse) {
        this.timestamp = timestamp;
        this.lowPressure = lowPressure;
        this.highPressure = highPressure;
        this.pulse = pulse;
    }

    @Override
    public String toString() {
        return "Timestamp='" + timestamp + '\'' +
                ", lowPressure='" + lowPressure + '\'' +
                ", highPressure='" + highPressure + '\'' +
                ", pulse='" + pulse + '\'' +
                '}';
    }

}
