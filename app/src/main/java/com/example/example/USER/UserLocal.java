package com.example.example.USER;

import com.example.example.model.User;

import lombok.Data;

@Data
public class UserLocal {

    private static User localUser;

    public String getKey(){
        return localUser.getAccess_token();
    }

    public static User getLocalUser() {
        return localUser;
    }

    public static void setLocalUser(User localUser) {
        UserLocal.localUser = localUser;
    }
}
