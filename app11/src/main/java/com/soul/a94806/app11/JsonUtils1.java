package com.soul.a94806.app11;

import com.google.gson.stream.JsonReader;

import java.io.StringReader;

public class JsonUtils1 {

    public void parseJson(String jsonData) {
        try {
            JsonReader reader = new JsonReader(new StringReader(jsonData));
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                while (reader.hasNext()) {
                    String tagName = reader.nextName();
                    if (tagName.equals("name")) {
                        System.out.println("name--->" + reader.nextString());
                    } else if (tagName.equals("age")) {
                        System.out.println("age--->" + reader.nextInt());
                    }
                }
                reader.endObject();
            }
            reader.endArray();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
