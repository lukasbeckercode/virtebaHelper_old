package com.example.virtebahelper;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button1;
    private String data; // a var to save the read data to

    private final String [] codesComplete = new String[156] ;   //A String array that saves each read line, Length=number of lines -1
    private final String [] code =new String[156]; //the number of the diagnosis
    private final String [] diag = new String[156]; //the diagnosis

    private InputStream stream; //a stream reader that reads a text file with all the diagnosis inside
    private BufferedReader reader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= findViewById(R.id.textView1); //Add the TextView
        textView.setMovementMethod(new ScrollingMovementMethod()); //make it scrollable
        button1=findViewById(R.id.button1);//add the button
        stream = this.getResources().openRawResource(R.raw.pzc); //get the file
        reader = new BufferedReader(new InputStreamReader(stream));

        final Thread readThread = new Thread(new Runnable() { //using a thread here to speed things up
            @Override
            public void run() {
              readFunction(); //calling read function because of anonymous inner class
        }


        });
        readThread.start(); //start the thread
        while (readThread.isAlive()) //wait until the thread is done
        {
            System.out.print('.');
        }

        button1.setOnClickListener(new View.OnClickListener() { //event handler for the button
            @Override
            public void onClick(View v) {

                for (int i = 0; i<= 10; i++) //print the first 10 lines FOR DEBUGGING
                {
                    textView.append(code[i] + ":" + diag[i] + "\n");//append the read data to the TextView FOR DEBUGGING
                }

            }
        });

    }
    private void readFunction() //read function, necessary because of anonymous inner class in thread
    {
        if (stream != null) {
            try {

                for (int i = 0; (data = reader.readLine()) != null; i++) //read until every line is finished
                {
                    codesComplete[i] = data; //add the data to the Array
                }

                for (int i = 0; i < codesComplete.length; i++) {
                    code[i] = codesComplete[i].substring(0, codesComplete[i].indexOf("---")); //read the code
                    diag[i] = codesComplete[i].substring(codesComplete[i].indexOf("---") + 3); //read the diagnosis


                }


                stream.close(); //close the stream


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
