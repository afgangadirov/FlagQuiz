package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewNetice, textViewNeticeFaiz;
    private Button buttonTekrar;
    private int dogruSaygac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewNetice = findViewById(R.id.textViewNetice);
        textViewNeticeFaiz = findViewById(R.id.textViewNeticeFaiz);
        buttonTekrar = findViewById(R.id.buttonTekrar);



        dogruSaygac = getIntent().getIntExtra("dogruSaygac",0);
        textViewNetice.setText(dogruSaygac+" DOGRU "+(5-dogruSaygac)+"YANLIS");
        textViewNeticeFaiz.setText((dogruSaygac*100/5)+" % UGUR");




        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this,QuizActivity.class));
                finish();
            }
        });
    }
}