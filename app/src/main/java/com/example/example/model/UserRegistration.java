package com.example.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistration {

    public String username;
    public String email;
    public String password1;
    public String password2;
    public String first_name;
    public String last_name;


}
