package com.example.virtebahelper;


import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView textView;
    private EditText number;
    private EditText date;
    private EditText age;


    private final String [] codesComplete = new String[156] ;   //A String array that saves each read line, Length=number of lines -1
    private final String [] code =new String[156]; //the number of the diagnosis
    private final String [] diag = new String[156]; //the diagnosis

    private InputStream stream; //a stream reader that reads a text file with all the diagnosis inside
    private BufferedReader reader;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= findViewById(R.id.textView1); //Add the TextView
        textView.setMovementMethod(new ScrollingMovementMethod()); //make it scrollable

        number = findViewById(R.id.editText);
        stream = this.getResources().openRawResource(R.raw.pzc); //get the file
        reader = new BufferedReader(new InputStreamReader(stream));
        date = findViewById(R.id.editTextDate);
        Button calcDateBtn = findViewById(R.id.buttonCalc);
        age = findViewById(R.id.editTextAge);

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
        calcDateBtn.setOnClickListener((v)->{
            String getDate ;
            String getDay = "0";
            String getMonth ="0";
            String getYear="0";


            if(!date.getText().toString().equals(""))
            {
                 getDate =  date.getText().toString();
                 try {
                     if(getDate.contains(".")) {
                         getDay = getDate.substring(0, getDate.indexOf("."));
                         getMonth = getDate.substring(getDate.indexOf(".") + 1, getDate.lastIndexOf("."));
                         getYear = getDate.substring(getDate.lastIndexOf(".") + 1);
                     } else if (getDate.contains("/"))
                     {
                         getDay = getDate.substring(0, getDate.indexOf("/"));
                         getMonth = getDate.substring(getDate.indexOf("/") + 1, getDate.lastIndexOf("/"));
                         getYear = getDate.substring(getDate.lastIndexOf("/") + 1);

                     } else age.setText("Format DD.MM.YYYY verwenden");

                 } catch (Exception e) {

                     e.printStackTrace(); //Debugging, REMOVE FOR FINAL BUILD
                     age.setText("Format DD.MM.YYYY verwenden");
                 }
                 try {
                     int i = calcAge(Integer.parseInt(getDay),Integer.parseInt(getMonth), Integer.parseInt(getYear));
                     age.setText(String.valueOf(i));
                 } catch (Exception e)
                 {
                     e.printStackTrace();
                     age.setText("Format DD.MM.YYYY verwenden");
                 }


            } else {
                age.setText("Format DD.MM.YYYY verwenden");
            }



           System.out.println(getDay+"/"+getMonth+"/"+getYear);//DEBUGGING
        });


    }
    private int calcAge(int d, int m, int y)
    {
        int age;
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            int curDay = calendar.get(Calendar.DAY_OF_MONTH);
            int curMonth = calendar.get(Calendar.MONTH)+1; //Months are indexed from 0!
            int curYear = calendar.get(Calendar.YEAR);

            System.out.println(curDay+"/"+curMonth+"/"+curYear);//DEBUGGING
        if(y<1000 && y>19 )
        {
            y +=1900;
        }else if (y<19)
        {
            y +=2000;
        }
        age = curYear - y;
        if(m<curMonth)
        {
            if (d < curDay) {
                age--;
            }
        }

        return age;
    }
}
