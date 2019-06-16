/*
* This is the launch Activity.
*/

package com.example.virtebahelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class StartUpActivity extends AppCompatActivity {

    //Button Variables
    Button openSearchBtn;
    Button openChirBtn;
    Button openInternBtn;
    Button openNeuroBtn;
    Button openPsychBtn;
    Button openGynBtn;
    Button openSonstBtn;
    Button openAgeCalcBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        //Create the Buttons
        openSearchBtn = findViewById(R.id.openSearchBtn);
        openChirBtn = findViewById(R.id.openChirBtn);
        openInternBtn = findViewById(R.id.openInternBtn);
        openNeuroBtn = findViewById(R.id.openNeuroBtn);
        openPsychBtn = findViewById(R.id.openPsychBtn);
        openGynBtn = findViewById(R.id.openGynBtn);
        openSonstBtn = findViewById(R.id.openSonstBtn);
        openAgeCalcBtn = findViewById(R.id.openAgeCalcBtn);

        //Assign the Buttons
        openSearchBtn.setOnClickListener((v)->openSearchActivity());
        openChirBtn.setOnClickListener(v->openChirAcitivty());
        openInternBtn.setOnClickListener(v->openInternActivity());
        openNeuroBtn.setOnClickListener(v->openNeuroActivity());
        openPsychBtn.setOnClickListener(v->openPsychActivity());
        openGynBtn.setOnClickListener(v->openGynActivity());
        openSonstBtn.setOnClickListener(v->openSonstActivity());
        openAgeCalcBtn.setOnClickListener(v->openAgeCalcActivity());
    }

    private void openSearchActivity(){
        Intent openSearchIntent = new Intent(this,SearcherActivity.class);
        startActivity(openSearchIntent);
    }

    private void openChirAcitivty() {
        Intent openChirIntent = new Intent(this,ChirActivity.class);
        startActivity(openChirIntent);
    }

    private void openInternActivity(){
        Intent openInternIntent = new Intent(this,InternActivity.class);
        startActivity(openInternIntent);
    }

    private void openNeuroActivity(){
        Intent openNeuroIntent = new Intent(this,NeuroActivity.class);
        startActivity(openNeuroIntent);
    }

    private void openPsychActivity(){
        Intent openPsychIntent = new Intent(this,PsychActivity.class);
        startActivity(openPsychIntent);
    }
    private void openGynActivity(){
        Intent openGynIntent = new Intent(this,GynActivity.class);
        startActivity(openGynIntent);
    }
    private void openSonstActivity(){
        Intent openSonstIntent = new Intent(this,SonstActivity.class);
        startActivity(openSonstIntent);
    }
    private void openAgeCalcActivity(){
        Intent openAgeCalcIntent = new Intent(this,AgeCalcActivity.class);
        startActivity(openAgeCalcIntent);
    }
}
