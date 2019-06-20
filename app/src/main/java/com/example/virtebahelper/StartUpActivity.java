/*
An Android App that helps EMT´s in Austria with VirtEBA.
Also an age calculator
Copyright: Lukas Becker, 2019

This is the launcher Class (i.e. the main menu)
*/


package com.example.virtebahelper;

//imports handled by IntelliJ
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StartUpActivity extends AppCompatActivity {
    private InputStream stream; //a stream reader that reads a text file with all the diagnosis inside
    private BufferedReader reader;
    private final String [] compCodes = new String[156]; //the entire text of pzc.txt is saved here
    //These will be accessed bya all the other Activities!
    public static final String [] allDiag = new String[156]; //only the diagnosis text is saved here
    public static final String [] allCodes = new String[156]; //only the pzc´s are saved here
    public static int kat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        //Create the Buttons
        //Button Variables
        Button openSearchBtn = findViewById(R.id.openSearchBtn);
        Button openChirBtn = findViewById(R.id.openChirBtn); // 1
        Button openInternBtn = findViewById(R.id.openInternBtn); //2
        Button openNeuroBtn = findViewById(R.id.openNeuroBtn); //3
        Button openPsychBtn = findViewById(R.id.openPsychBtn); //4
        Button openGynBtn = findViewById(R.id.openGynBtn); //5
        Button openSonstBtn = findViewById(R.id.openSonstBtn);//6
        Button openAgeCalcBtn = findViewById(R.id.openAgeCalcBtn);

        readData(); //call the Method to read the Textfile (argument is the location of the file)

        //Assign the Buttons
        openSearchBtn.setOnClickListener((v)->openSearchActivity());
        openChirBtn.setOnClickListener(v->{
            openCategoryViewActivity();
            kat = 1;
        });
        openInternBtn.setOnClickListener(v->{
           openCategoryViewActivity();
            kat = 2;
        });
        openNeuroBtn.setOnClickListener(v->{
           openCategoryViewActivity();
            kat = 3;
        });
        openPsychBtn.setOnClickListener(v->{
           openCategoryViewActivity();
            kat = 4;
        });
        openGynBtn.setOnClickListener(v->{
            openCategoryViewActivity();
            kat = 5;
        });
        openSonstBtn.setOnClickListener(v->{
            openCategoryViewActivity();
            kat = 6;
        });
        openAgeCalcBtn.setOnClickListener(v->openAgeCalcActivity());
    }

    //All these methods open the corresponding Activity(=class)

    private void openSearchActivity(){
        Intent openSearchIntent = new Intent(this,SearcherActivity.class);
        startActivity(openSearchIntent);
    }

    private void openCategoryViewActivity() {
        Intent openChirIntent = new Intent(this, CategoryViewActivity.class);
        startActivity(openChirIntent);
    }


    private void openAgeCalcActivity(){
        Intent openAgeCalcIntent = new Intent(this,AgeCalcActivity.class);
        startActivity(openAgeCalcIntent);
    }

    //this method reads the pzc.txt file
    private void readData(){
        stream = this.getResources().openRawResource(R.raw.pzc); //get the file
        reader = new BufferedReader(new InputStreamReader(stream));

        final Thread readThread = new Thread(()->{
            if (stream != null) {
                try {

                    // a var to save the read data to
                    String data;
                    for (int i = 0; (data = reader.readLine()) != null; i++) //read until every line is finished
                    {
                        compCodes[i] = data; //add the data to the Array
                    }

                    for (int i = 0; i < compCodes.length; i++) {

                        allCodes[i] = compCodes[i].substring(0, compCodes[i].indexOf("---")); //read the code
                        allDiag[i] = compCodes[i].substring(compCodes[i].indexOf("---") + 3); //read the diagnosis

                    }

                    stream.close(); //close the stream

                } catch (Exception e) {
                    e.printStackTrace(); //remove for final build
                }
            }
        });
        readThread.start();
        while (readThread.isAlive()) //wait for the thread to finish(possibly useless, IDK)
        {

        }
    }
}
