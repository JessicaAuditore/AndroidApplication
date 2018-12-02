package com.soul.a94806.app11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String jsonData1 = "[{\"name\":\"Michael\",\"age\":20},{\"name\":\"Mike\",\"age\":21}]";
    private String jsonData2 = "{\"name\":\"Michael\",\"age\":20}";

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            JsonUtils1 jsonUtils1 = new JsonUtils1();
            jsonUtils1.parseJson(jsonData1);
            JsonUtils2 jsonUtils2 = new JsonUtils2();
            jsonUtils2.parseUserFromJson(jsonData2);
            JsonUtils3 jsonUtils3 = new JsonUtils3();
            jsonUtils3.parseUserFromJson(jsonData1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new ButtonListener());
    }
}
