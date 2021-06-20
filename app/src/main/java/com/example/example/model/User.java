package com.example.example.model;


import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {


    private String access_token;
    private String refresh_token;

    private UserData user;

    public int getId() {
        return user.getId();
    }
    public String getUserName() {
        return user.getUsername();
    }
    public String getUserFirstName() {
        return user.getFirst_name();
    }
    public String getUserLastName() {
        return user.getLast_name();
    }
    public String getEmail() {
        return user.getEmail();
    }

    @Data
    class UserData{

        @SerializedName("pk")
        private int id;

        public String username;

        public String email;

        public String first_name;

        public String last_name;

    }

}
/*      "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjI0MDQzMzM1LCJqdGkiOiJjZjI4MGEwZDQ4NzE0MGM0YWNhMGQwMjYyMzI3NTVmYyIsInVzZXJfaWQiOjF9.kMDr-dAACjU5KwWyhu3ooLq8qX-LQEbkrIHE_RzIfu0",
        "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTYyNDEyOTEzNSwianRpIjoiMWM1NTMxMDY2MmEzNDgyZjhhODk0YThkYzJiOTVhMjIiLCJ1c2VyX2lkIjoxfQ.zD8WRy1JumOyP845aqBhCn8xMP1p4FKrJJDeA7GHPNY",
        "user": {
        "pk": 1,
        "username": "+79960651412",
        "first_name": "ilya",
        "last_name": "kochankov"
        }*/
