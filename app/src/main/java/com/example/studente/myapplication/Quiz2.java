package com.example.studente.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz2 extends LocalizationActivity {
    private String lingua="";
    private String text="";
    private TextView tempoo;
    private TextView domanda;
    private ArrayList <String> extra;
    private Button btn1;
    private Button btn2;
    private RequestQueue mQueue;
    private int count;
    private int correct_answers;
    private int incorrect_answers;
    private TextView tv2;
    long secondi;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        extra= new ArrayList<>();
        super.onCreate(savedInstanceState);
        CardView carta=findViewById(R.id.cardView);

        setContentView(R.layout.activity_quiz2);
        Bundle b = getIntent().getExtras();
        if (null != b) {
            extra = b.getStringArrayList("array_list");

        }
        domanda=(TextView)findViewById(R.id.textView25);
        lingua=extra.get(3);
        btn1=findViewById(R.id.button9);
        btn2=findViewById(R.id.button10);
        mQueue = Volley.newRequestQueue(this);
        String domande=extra.get(0);
        String categoria=extra.get(1);
        String difficoltà=extra.get(2);
        String lingua=extra.get(3);
        int questions=Integer.parseInt(domande);

        String url=findUrl(domande,categoria,difficoltà);
        jsonParse(url,questions, difficoltà);

    }
    private void jsonParse(String url, final int domande, final String difficoltà) {
        tempoo= (TextView)findViewById(R.id.textView18);
        tv2=(TextView)findViewById(R.id.textView10);
        count=0;
        correct_answers=0;
        long tempo=0;
        if (domande==10) tempo=90000;
        if (domande==20) tempo=180000;
        if (domande==30) tempo=270000;
        if (domande==40) tempo=360000;
        if (domande==50) tempo=450000;
        CountDownTimer timer=new CountDownTimer(tempo, 1000) {

            public void onTick(long millisUntilFinished) {
                if (lingua.equals("it")) {
                    tempoo.setText("Secondi rimanenti: " + millisUntilFinished / 1000);
                }
                else {
                    tempoo.setText("Seconds remaining: " + millisUntilFinished / 1000);
                }
                secondi=millisUntilFinished/1000;
            }


            public void onFinish() {
                final ArrayList<String> extra2= new ArrayList<>();
                extra2.add("RESULTS");
                extra2.add(String.valueOf(correct_answers));
                extra2.add(String.valueOf(incorrect_answers));
                extra2.add("1");
                extra2.add(difficoltà);
                extra2.add(lingua);
                Intent intent = new Intent(Quiz2.this, Results.class);
                intent.putExtra("array_list", extra2);
                startActivity(intent);
                finish();
            }

        }.start();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {

                try {

                    if (count<domande) {
                        btn1.setTextColor(Color.BLACK);
                        btn2.setTextColor(Color.BLACK);

                        if (lingua.equals("it")) {
                            tv2.setText("DOMANDA N. "+String.valueOf(count+1));
                        }
                        else {
                            tv2.setText("QUESTION N. "+String.valueOf(count+1));
                        }
                        ArrayList<String> vetdomande = new ArrayList<>();
                        final String correct=response.getJSONArray("results").getJSONObject(count).getString("correct_answer");
                        domanda.setText(response.getJSONArray("results").getJSONObject(count).getString("question"));
                        vetdomande.add(correct);
                        vetdomande.add(response.getJSONArray("results").getJSONObject(count).getJSONArray("incorrect_answers").getString(0));

                        Collections.shuffle(vetdomande);
                        Log.d("TAG", vetdomande.get(0));



                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count++;
                                if (btn1.getText().equals(correct)) {
                                    correct_answers++;
                                    btn1.setTextColor(Color.GREEN);
                                    final Handler handle = new Handler();
                                    Runnable delay = new Runnable() {
                                        public void run() {
                                            onResponse(response);
                                        }
                                    };
                                    handle.postDelayed(delay,1000);

                                }
                                else {
                                    incorrect_answers++;
                                    btn1.setTextColor(Color.RED);
                                    final Handler handle = new Handler();
                                    Runnable delay = new Runnable() {
                                        public void run() {
                                            onResponse(response);
                                        }
                                    };
                                    handle.postDelayed(delay,1000);
                                }

                            }
                        });

                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count++;
                                if (btn2.getText().equals(correct)) {
                                    correct_answers++;
                                    btn2.setTextColor(Color.GREEN);
                                    final Handler handle = new Handler();
                                    Runnable delay = new Runnable() {
                                        public void run() {
                                            onResponse(response);
                                        }
                                    };
                                    handle.postDelayed(delay,1000);

                                }
                                else {
                                    incorrect_answers++;
                                    btn2.setTextColor(Color.RED);
                                    final Handler handle = new Handler();
                                    Runnable delay = new Runnable() {
                                        public void run() {
                                            onResponse(response);
                                        }
                                    };
                                    handle.postDelayed(delay,1000);
                                }

                            }
                        });


                    }
                    else {
                        final ArrayList<String> extra2= new ArrayList<>();
                        extra2.add("RESULTS");
                        extra2.add(String.valueOf(correct_answers));
                        extra2.add(String.valueOf(incorrect_answers));
                        extra2.add(String.valueOf(secondi));
                        extra2.add(difficoltà);
                        extra2.add(lingua);
                        Intent intent = new Intent(Quiz2.this, Results.class);
                        intent.putExtra("array_list", extra2);
                        startActivity(intent);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
    @Override
    public void onBackPressed() {
        Context context = getApplicationContext();
        if (lingua.equals("en")) {
            text = "Complete the quiz!";
        }
        if (lingua.equals("it")) {
            text="Completa il quiz!";
        }
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private String findUrl(String domande, String categoria, String difficoltà) {
        String url = "https://opentdb.com/api.php?"+"amount="+domande;
        if (categoria.equals("Any category")||categoria.equals("Tutte le categorie")) {

        }
        else {
            switch (categoria) {
                case "General Knowledge" :
                    url=url+"&category=9";
                    break;
                case "Entertainment: books":
                    url=url+"&category=10";
                    break;
                case "Entertainment: film":
                    url=url+"&category=11";
                    break;
                case "Entertainment: music":
                    url=url+"&category=12";
                    break;
                case "Entertainment: television":
                    url=url+"&category=14";
                    break;
                case "Entertainment: videogames":
                    url=url+"&category=15";
                    break;
                case "Science: nature":
                    url=url+"&category=17";
                    break;
                case "Science: computers":
                    url=url+"&category=18";
                    break;
                case "Science: Mathematics":
                    url=url+"&category=19";
                    break;
                case "Sports":
                    url=url+"&category=21";
                    break;
                case "Geography":
                    url=url+"&category=22";
                    break;
                case "History":
                    url=url+"&category=23";
                    break;
                case "Mithology":
                    url=url+"&category=20";
                    break;
                case "Politics":
                    url=url+"&category=24";
                    break;
                case "Art":
                    url=url+"&category=25";
                    break;
                case "Celebrities":
                    url=url+"&category=26";
                    break;
                case "Animals":
                    url=url+"&category=27";
                    break;
            }
        }

        if (difficoltà.equals("Any difficulty")) {

        }
        else {
            switch (difficoltà){
                case "Easy":
                    url=url+"&difficulty=easy";
                    break;
                case "Medium":
                    url=url+"&difficulty=medium";
                    break;
                case "Hard":
                    url=url+"&difficulty=hard";
                    break;
            }
        }

        url=url+"&type=boolean";

        return url;
    }
}
