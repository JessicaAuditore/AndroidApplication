package com.soul.a94806.app11;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;

public class JsonUtils3 {

    public void parseUserFromJson(String jsonData1){
        Type listType=new TypeToken<LinkedList<User>>(){}.getType();
        Gson gson=new Gson();
        LinkedList<User> users=gson.fromJson(jsonData1,listType);
        for(Iterator<User> iterator=users.iterator();iterator.hasNext();){
            User user=(User)iterator.next();
            System.out.println("name--->"+user.getName());
            System.out.println("age--->"+user.getAge());
        }
    }
}
