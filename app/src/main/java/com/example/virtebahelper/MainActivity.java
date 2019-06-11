package com.example.virtebahelper;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView textView;
    private EditText number;


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

        number = findViewById(R.id.editText);
        stream = this.getResources().openRawResource(R.raw.pzc); //get the file
        reader = new BufferedReader(new InputStreamReader(stream));


       /* final Thread readThread = new Thread(new Runnable() { //using a thread here to speed things up
            @Override
            public void run() {
              readFunction(); //calling read function because of anonymous inner class
        }

        });*/
        final Thread readThread = new Thread(()->{
            if (stream != null) {
                try {

                    // a var to save the read data to
                    String data;
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
        });
        readThread.start(); //start the thread
        //noinspection StatementWithEmptyBody
        while (readThread.isAlive()) //wait until the thread is done
        {
           // System.out.print('.'); For Debugging
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diag);
        textView.setAdapter(adapter);
     textView.setOnItemClickListener((parent, view, position, id) -> {
        String selectedItem = textView.getText().toString();
         int pos = Arrays.asList(diag).indexOf(selectedItem);
         number.setText(code[pos]);

     });


    }
}
