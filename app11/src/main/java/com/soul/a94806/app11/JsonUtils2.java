package com.soul.a94806.app11;

import com.google.gson.Gson;

public class JsonUtils2 {

    public void parseUserFromJson(String jsonData2) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonData2, User.class);
        System.out.println("name--->"+user.getName());
        System.out.println("age--->"+user.getAge());
    }
}
