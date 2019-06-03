package com.example.virtebahelper;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= findViewById(R.id.textView1); //Add the TextView
        textView.setMovementMethod(new ScrollingMovementMethod()); //make it scrollable

        String data ; // a var to save the read data to

        String codesComplete [] = new String[44]; //A String array that saves each read line, Length=number of lines -1
        String code; //the number of the diagnosis
        String diag; //the diagnosis

        InputStream stream = this.getResources().openRawResource(R.raw.pzc); //a stream reader that reads a text file with all the diagnosis inside
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        if(stream!=null){
            try {
                for(int i= 0; (data = reader.readLine())!=null;i++) //read until every line is finished
                {
                    codesComplete[i] = data; //add the data to the Array
                }

                for(int i=0; i <= codesComplete.length;i++)
                {
                    code = codesComplete[i].substring(0,codesComplete[i].indexOf("---")); //read the code
                    diag = codesComplete[i].substring(codesComplete[i].indexOf("---")+3); //read the diagnosis

                    textView.append(code+":"+diag+"\n");//append the read data to the TextView FOR DEBUGGING
                }



                stream.close(); //close the stream
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
