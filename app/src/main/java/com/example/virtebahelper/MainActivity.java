/*
An Android App that helps EMT´s in Austria with VirtEBA.
Also an age calculator
Copyright: Lukas Becker, 2019
*/

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

    //UI Variables
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
        setContentView(R.layout.activity_main); //set the view to activity.main

        //Add the UI Items
        textView= findViewById(R.id.textView1);
        number = findViewById(R.id.editText);
        date = findViewById(R.id.editTextDate);
        Button calcDateBtn = findViewById(R.id.buttonCalc);
        age = findViewById(R.id.editTextAge);

        textView.setMovementMethod(new ScrollingMovementMethod()); //make the textView scrollable


        stream = this.getResources().openRawResource(R.raw.pzc); //get the file
        reader = new BufferedReader(new InputStreamReader(stream));


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

        //convert pzc.txt to fit into the textView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diag);
        textView.setAdapter(adapter);

        //Listener for when somebody clicks any item of the textView
     textView.setOnItemClickListener((parent, view, position, id) -> {
        String selectedItem = textView.getText().toString(); //save the selected item to a local variable
         int pos = Arrays.asList(diag).indexOf(selectedItem); // save the position of the selection within the Array to pos
         number.setText(code[pos]); //display the corresponding number to the chosen item

     });

        //Listener for when somebody clicks the Button
        calcDateBtn.setOnClickListener((v)->{

            String getDate ; //Date
            String getDay = "0"; //Day to be extracted out of Date
            String getMonth ="0"; //Month to be extracted out of Date
            String getYear="0"; //Year to be extracted out of Date


            if(!date.getText().toString().equals("")) //Check if something is written inside the field
            {
                 getDate =  date.getText().toString(); //save the input to the local variable
                 try { //users are stupid, so we use this to avoid the app crashing

                     //There are two allowed separators / and . :
                     if(getDate.contains(".")) { //if the . is used

                         getDay = getDate.substring(0, getDate.indexOf(".")); //first number in front of the .
                         getMonth = getDate.substring(getDate.indexOf(".") + 1, getDate.lastIndexOf(".")); //number between the two .
                         getYear = getDate.substring(getDate.lastIndexOf(".") + 1); //number after the last .
                     } else if (getDate.contains("/")) // do the same thing but with /
                     {
                         getDay = getDate.substring(0, getDate.indexOf("/"));
                         getMonth = getDate.substring(getDate.indexOf("/") + 1, getDate.lastIndexOf("/"));
                         getYear = getDate.substring(getDate.lastIndexOf("/") + 1);

                     } else age.setText("Format DD.MM.YYYY verwenden"); //if something weird is inputted, tell the user they messed up

                 } catch (Exception e) {

                     e.printStackTrace(); //Debugging, REMOVE FOR FINAL BUILD
                     age.setText("Format DD.MM.YYYY verwenden");//if something weird is inputted, tell the user they messed up
                 }
                 try {
                     int i = calcAge(Integer.parseInt(getDay),Integer.parseInt(getMonth), Integer.parseInt(getYear)); //call the method calcAge, parse the Strings to ints
                     age.setText(String.valueOf(i)); //tell the user the age
                 } catch (Exception e)
                 {
                     e.printStackTrace(); //Debugging, REMOVE FOR FINAL BUILD
                     age.setText("Format DD.MM.YYYY verwenden");//if something weird is inputted, tell the user they messed up
                 }


            } else {
                age.setText("Format DD.MM.YYYY verwenden");//if something weird is inputted, tell the user they messed up
            }



           System.out.println(getDay+"/"+getMonth+"/"+getYear);//DEBUGGING
        });


    }
    private int calcAge(int d, int m, int y) //this method calculates the age
    {
        int age;

            Calendar calendar = Calendar.getInstance(TimeZone.getDefault()); //create a calender object that uses the devices default time zone
            int curDay = calendar.get(Calendar.DAY_OF_MONTH); //get the current day
            int curMonth = calendar.get(Calendar.MONTH)+1; //Months are indexed from 0!
            int curYear = calendar.get(Calendar.YEAR); //get the current year

            System.out.println(curDay+"/"+curMonth+"/"+curYear);//DEBUGGING

        //if the user enters YY instead of YYYY, we have to do this:
        if(y<1000 && y>19 ) //below 1000 means YY, above 19 means the person is probably old
        {
            y +=1900;
        }else if (y<19) // below 1000 = below 19 means YY, below 19 means the person is probably a child (CAVE: or very old)
        {
            y +=2000;
        }
        age = curYear - y; // calculate the age

        //if the person hadn´t had their birthday yet, we need to subtract 1 year from their age
        if(m<curMonth)
        {
            if (d < curDay) {
                age--;
            }
        }

        return age; //return the age
    }
}
