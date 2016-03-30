package com.example.chaitanya.servicesexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by chaitanyatanna on 3/11/16.
 */
public class SecondActivity extends AppCompatActivity {

    Button buttonStart, buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);

        buttonStart = (Button) findViewById(R.id.btn_start_service_second);
        buttonNext = (Button) findViewById(R.id.btn_next);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MyIntentService.class);
                startService(intent);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(i);
            }
        });
    }
}
