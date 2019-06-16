/*
"Gyn-Pädiatrie-Codes"-Class
uses variables from StartUpActivity-Class
for better comments, look at ChirActivity and SonstActivity!
 */

package com.example.virtebahelper;

//IntelliJ Imports
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Arrays;

public class GynActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyn);
        Button backBtn = findViewById(R.id.backBtnGyn);
        backBtn.setOnClickListener(v -> goBack());
        EditText text = findViewById(R.id.editTextGyn);
        text.setEnabled(false);
        String [] gynDiag = new String[156];
        String [] gynCodes = new String[156];

        int i = 0;
        for(String c:StartUpActivity.allCodes)
        {
            if(c.startsWith("5")) //5=gyn & children
            {
                gynCodes[i] = c;
                int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                gynDiag[i] = StartUpActivity.allDiag[pos];
            }
            i++;
        }


        for(String s:gynCodes)
        {
            if(s != null)
            {
                int pos = Arrays.asList(gynCodes).indexOf(s);
                text.append(s+": "+gynDiag[pos]+"\n");
            }

        }

    }
    private void goBack()
    {
        Intent goBackIntent = new Intent(this,StartUpActivity.class);
        startActivity(goBackIntent);
    }
}
