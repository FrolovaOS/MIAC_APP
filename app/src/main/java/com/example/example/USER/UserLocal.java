package com.example.example.USER;

import com.example.example.model.User;

import lombok.Data;

@Data
public class UserLocal {

    private static User localUser;

    public static int getId(){
        return localUser.getId();
    }

    public static String getKey(){
        return "Bearer " + localUser.getAccess_token();
    }

    public static User getLocalUser() {
        return localUser;
    }

    public static void setLocalUser(User localUser) {
        UserLocal.localUser = localUser;
    }
}
