package com.example.studente.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        final Handler handle = new Handler();
        Runnable delay = new Runnable() {
            public void run() {
                Intent intent= new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        };

        handle.postDelayed(delay,3000);

    }
}
