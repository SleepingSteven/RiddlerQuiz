package com.example.studente.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;

import java.util.ArrayList;

public class Results extends LocalizationActivity {
    TextView risultati;
    TextView corrette;
    TextView sbagliate;
    TextView tempo;
    TextView punti;
    TextView bonus;
    TextView totali;
    ArrayList<String> extra=new ArrayList<>();
    Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        risultati=findViewById(R.id.textView12);
        corrette=findViewById(R.id.textView19);
        sbagliate=findViewById(R.id.textView20);
        tempo=findViewById(R.id.textView21);
        punti=findViewById(R.id.textView22);
        bonus=findViewById(R.id.textView23);
        totali=findViewById(R.id.textView24);
        btn=findViewById(R.id.button8);
        Bundle b = getIntent().getExtras();
        if (null != b) {
            extra = b.getStringArrayList("array_list");
        }
        setLanguage(extra.get(5));
        corrette.setText(extra.get(1));
        sbagliate.setText(extra.get(2));
        tempo.setText(extra.get(3)+" sec.");
        int corrette= Integer.parseInt(extra.get(1));
        int sbagliate= Integer.parseInt(extra.get(2));
        int tempo= Integer.parseInt(extra.get(3));
        int punt=(corrette*tempo)-sbagliate;
        punti.setText(String.valueOf(punt));
        int bonu=0;
        if (extra.get(4).equals("Easy"))  bonu=100;
        if (extra.get(4).equals("Medium")) bonu=200;
        if (extra.get(4).equals("Hard")) bonu=300;
        bonus.setText("x"+String.valueOf(bonu));
        int tot=punt*bonu;
        totali.setText(String.valueOf(tot)+"pts.");
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int puntMax=prefs.getInt("record",0);
        if (tot>puntMax) {
            AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            if (extra.get(5).equals("it")) {
                myAlert.setMessage("Nuovo record!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                    myAlert.show();
            }
            if (extra.get(5).equals("en")) {
                myAlert.setMessage("New record!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create();
                myAlert.show();
            }
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
            editor.putInt("record",tot);
            editor.apply();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Results.this,NewActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }
}
