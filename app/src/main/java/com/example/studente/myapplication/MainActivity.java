package com.example.studente.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;

public class MainActivity extends LocalizationActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLang1= findViewById(R.id.button);
        btnLang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
                Intent intent= new Intent(MainActivity.this,NewActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT,"en");
                startActivity(intent);

            }
        });
        Button btnLang2= findViewById(R.id.button2);
        btnLang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("it");
                Intent intent= new Intent(MainActivity.this,NewActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT,"it");
                startActivity(intent);
            }
        });
    }
}
