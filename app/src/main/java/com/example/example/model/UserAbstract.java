package com.example.example.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public abstract class UserAbstract {

    private String username,
            email,
            first_name,
            last_name;

}
