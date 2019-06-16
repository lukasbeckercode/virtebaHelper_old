/*
"Neuro-Codes"-Class
uses variables from StartUpActivity-Class
for better comments, look at ChirActivity and SonstActivity!
 */
package com.example.virtebahelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Arrays;

public class NeuroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuro);
        Button backBtn = findViewById(R.id.backBtnNeuro);

        EditText text = findViewById(R.id.editTextNeuro);
        text.setEnabled(false);
        String [] neuroDiag = new String[156];
        String [] neuroCodes = new String[156];

        int i = 0;
        for(String c:StartUpActivity.allCodes)
        {
            if(c.startsWith("41") || c.startsWith("42")) //41=neuro; 42=insult
            {
                neuroCodes[i] = c;
                int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                neuroDiag[i] = StartUpActivity.allDiag[pos];
            }
            i++;
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
