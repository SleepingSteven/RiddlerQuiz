package com.example.studente.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;

import java.util.ArrayList;

public class ReadyActivity2 extends LocalizationActivity {
    private TextView tv1;
    private TextView tv2;
    private ArrayList<String> extra;
    private String lingua="";
    private String messaggio="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);
        tv1=(TextView)findViewById(R.id.textView29);
        tv2=findViewById(R.id.textView31);
        Bundle b = getIntent().getExtras();
        if (null != b) {
            extra = b.getStringArrayList("array_list");

        }
        lingua=extra.get(3);
        setLanguage(extra.get(3));
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int puntMax=prefs.getInt("record",0);
        tv1.setText(puntMax+"");

        CountDownTimer timer=new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

                    tv2.setText(String.valueOf(millisUntilFinished/1000));

            }


            public void onFinish() {
                Intent intent = new Intent(ReadyActivity2.this, Quiz2.class);
                intent.putExtra("array_list", extra);
                startActivity(intent);
                cancel();
                finish();

            }

        }.start();
    }

    public void onBackPressed() {
        Context context = getApplicationContext();
        if (lingua.equals("en")) {
            messaggio = "Complete the quiz!";
        }
        if (lingua.equals("it")) {
            messaggio = "Completa il quiz!";
        }
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, messaggio, duration);
        toast.show();
    }
}
