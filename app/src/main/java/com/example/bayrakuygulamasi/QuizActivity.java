package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewDogru, textViewYanlis, textViewSualSay;
    private ImageView imageViewBayraq;
    private Button buttonA,buttonB,buttonC,buttonD;

    private ArrayList<Bayraklar> suallarList;
    private ArrayList<Bayraklar> yanlisSecimlerList;
    private Bayraklar dogruSual;
    private Veritabani vt;
    //Saygaclar
    private int sualSaygac = 0;
    private int dogruSaygac = 0;
    private int yanlisSaygac = 0;
    //Secimler
    private HashSet<Bayraklar> secimleriQaristirmaList = new HashSet<>();
    private ArrayList<Bayraklar> secimlerList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSualSay = findViewById(R.id.textViewSualSay);
        imageViewBayraq = findViewById(R.id.imageViewBayraq);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        vt = new Veritabani(this);

        suallarList = new BayraklarDao().rasgele5Getir(vt);


        sualYukle();




        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonA);
                saygacKontrol();

            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonB);
                saygacKontrol();

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonC);
                saygacKontrol();

            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonD);
                saygacKontrol();

            }
        });

    }



    public void sualYukle(){

        textViewSualSay.setText((sualSaygac+1)+". Sual");

        dogruSual = suallarList.get(sualSaygac);
        yanlisSecimlerList = new BayraklarDao().rasgele3YanlisSecenekGetir(vt,dogruSual.getBayrak_id());

        imageViewBayraq.setImageResource(getResources().getIdentifier(dogruSual.getBayrak_resim(),"drawable",getPackageName()));


        secimleriQaristirmaList.clear();
        secimleriQaristirmaList.add(dogruSual);
        secimleriQaristirmaList.add(yanlisSecimlerList.get(0));
        secimleriQaristirmaList.add(yanlisSecimlerList.get(1));
        secimleriQaristirmaList.add(yanlisSecimlerList.get(2));

        secimlerList.clear();
        for (Bayraklar b : secimleriQaristirmaList) {
            secimlerList.add(b);
        }

        buttonA.setText(secimlerList.get(0).getBayrak_ad());
        buttonB.setText(secimlerList.get(1).getBayrak_ad());
        buttonC.setText(secimlerList.get(2).getBayrak_ad());
        buttonD.setText(secimlerList.get(3).getBayrak_ad());

    }


    public void dogruKontrol(Button button){

        String buttonYazi = button.getText().toString();
        String dogruCavab = dogruSual.getBayrak_ad();

        if (buttonYazi == dogruCavab){
            dogruSaygac++;
        }else{
            yanlisSaygac++;
        }

        textViewDogru.setText("Dogru : "+dogruSaygac);
        textViewYanlis.setText("Yanlis : "+yanlisSaygac);


    }


    public void saygacKontrol(){
        sualSaygac++;

        if (sualSaygac !=5){
            sualYukle();
        }else{
            Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
            intent.putExtra("dogruSaygac",dogruSaygac);
            startActivity(intent);
            finish();
        }
    }




}