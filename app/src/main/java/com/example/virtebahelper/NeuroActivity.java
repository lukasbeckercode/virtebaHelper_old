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

public class NeuroActivity extends AppCompatActivity {
    private InputStream stream; //a stream reader that reads a text file with all the diagnosis inside
    private BufferedReader reader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuro);
        Button backBtn = findViewById(R.id.backBtnNeuro);

        EditText text = findViewById(R.id.editTextNeuro);

        String [] compCodes = new String[156];
        String [] neuroDiag = new String[156];
        String [] neuroCodes = new String[156];

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
                        if(compCodes[i].startsWith("41")||compCodes[i].startsWith("42"))
                        {
                            neuroCodes[i] = compCodes[i].substring(0, compCodes[i].indexOf("---")); //read the code
                            neuroDiag[i] = compCodes[i].substring(compCodes[i].indexOf("---") + 3); //read the diagnosis

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
        for(String s:neuroCodes)
        {
            if(s != null)
            {
                int pos = Arrays.asList(neuroCodes).indexOf(s);
                text.append(s+": "+neuroDiag[pos]+"\n");
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
