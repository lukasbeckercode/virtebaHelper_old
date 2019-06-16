package com.example.virtebahelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PsychActivity extends AppCompatActivity {
    private InputStream stream; //a stream reader that reads a text file with all the diagnosis inside
    private BufferedReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psych);
        Button backBtn = findViewById(R.id.backBtnPsych);

        EditText text = findViewById(R.id.editTextPsych);

        String [] compCodes = new String[156];
        String [] psychDiag = new String[156];
        String [] psychCodes = new String[156];


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
                        if(compCodes[i].startsWith("2"))
                        {
                            psychCodes[i] = compCodes[i].substring(0, compCodes[i].indexOf("---")); //read the code
                            psychDiag[i] = compCodes[i].substring(compCodes[i].indexOf("---") + 3); //read the diagnosis

                        }


                    }


                    stream.close(); //close the stream


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        readThread.start(); //start the thread
        while (readThread.isAlive())
        {

        }
        for(String s:psychCodes)
        {
            if(s != null)
            {
                int pos = Arrays.asList(psychCodes).indexOf(s);
                text.append(s+": "+psychDiag[pos]+"\n");
            }

        }

        backBtn.setOnClickListener(v -> goBack());
    }
    private void goBack()
    {
        Intent goBackIntent = new Intent(this,StartUpActivity.class);
        startActivity(goBackIntent);
    }
}
