/*
"Sonstige Codes"-Class: Augen, Reanimation, ZMK, Uro, Strahlen, Derma, Hyperbare Medizin, Derma, HNO
Reads the codes from the StartUpActivity-Class
 */

package com.example.virtebahelper;
//imports handled by IntelliJ
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Arrays;

public class SonstActivity extends AppCompatActivity {

    //create local variables to save the specific codes
    String [] sonstDiag = new String[156];
    String [] sonstCodes = new String[156];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonst);
        Button backBtn = findViewById(R.id.backBtnSonst);//generic "back" button

        EditText text = findViewById(R.id.editTextSonst);
        text.setEnabled(false);//design choice

         int i = 0; //counter variable for the Arrays
      for(String c:StartUpActivity.allCodes)
        {
            if(c.startsWith("1") || c.startsWith("7")) //1=CPR; 7=Misc. Codes
            {
                sonstCodes[i] = c; //save the code to the array
                int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c); //get the position of the code within the original array
                sonstDiag[i] = StartUpActivity.allDiag[pos]; //get the corresponding diagnosis
            }
            i++; //counter +1
        }


        for(String s:sonstCodes) //print the codes
        {
            if(s != null) //check if there is something written in the current array position
            {
                int pos = Arrays.asList(sonstCodes).indexOf(s); //look for the position of the code in the local variable
                text.append(s+": "+sonstDiag[pos]+"\n"); //PZC: DIAGNOSIS CRLF
            }

        }

        backBtn.setOnClickListener(v -> goBack()); //call the "go back" method
    }
    private void goBack() //generic "return to main menu" method
    {
        Intent goBackIntent = new Intent(this,StartUpActivity.class);
        startActivity(goBackIntent);
    }
}
