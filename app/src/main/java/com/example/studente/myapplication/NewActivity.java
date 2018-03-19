package com.example.studente.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;

import java.util.ArrayList;

public class NewActivity extends LocalizationActivity {
private String lingua="";
private String text="";
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        if (getIntent()!=null && getIntent().hasExtra(Intent.EXTRA_TEXT)) {
            lingua=getIntent().getStringExtra(Intent.EXTRA_TEXT);
            setLanguage(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }

       /* String[] arraySpinner = new String[] {
                "10","20","30","40","50"
        };

        String[] arraySpinner2 = new String[] {
          ""
        };*/

        String[] array1= getResources().getStringArray(R.array.questions);
        String[] array2= getResources().getStringArray(R.array.categories);
        String[] array3= getResources().getStringArray(R.array.difficulty);
        String[] array4= getResources().getStringArray(R.array.type);


        final Spinner s= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,array1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);

        final Spinner s2= (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,array2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s2.setAdapter(adapter2);

        final Spinner s3= (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,array3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s3.setAdapter(adapter3);

        final Spinner s4= (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,array4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s4.setAdapter(adapter4);

        Button bottone3= findViewById(R.id.button3);
        bottone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modalita=s4.getSelectedItem().toString();
                String domande=s.getSelectedItem().toString();
                String categoria=s2.getSelectedItem().toString();
                String difficolta=s3.getSelectedItem().toString();
                final ArrayList<String> extra= new ArrayList<>();
                extra.add(domande);
                extra.add(categoria);
                extra.add(difficolta);
                extra.add(lingua);

                    if ((modalita.compareTo("Multiple choice") == 0) || modalita.compareTo("Scelta multipla") == 0) {
                        Intent intent = new Intent(NewActivity.this, ReadyActivity.class);
                        intent.putExtra("array_list", extra);
                        startActivity(intent);
                        finish();
                    }
                    if ((modalita.compareTo("True / False") == 0) || modalita.compareTo("Vero / Falso") == 0) {
                        Intent intent = new Intent(NewActivity.this, ReadyActivity2.class);
                        intent.putExtra("array_list", extra);
                        startActivity(intent);
                        finish();
                    }
                }

        });
    }


}
