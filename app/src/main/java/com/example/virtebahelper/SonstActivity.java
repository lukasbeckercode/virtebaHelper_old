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

public class SonstActivity extends AppCompatActivity {

    String [] sonstDiag = new String[156];
    String [] sonstCodes = new String[156];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonst);
        Button backBtn = findViewById(R.id.backBtnSonst);

        EditText text = findViewById(R.id.editTextSonst);
        text.setEnabled(false);

         int i = 0;
      for(String c:StartUpActivity.allCodes)
        {
            if(c.startsWith("1") || c.startsWith("7"))
            {
                sonstCodes[i] = c;
                int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                sonstDiag[i] = StartUpActivity.allDiag[pos];
            }
            i++;
        }


        for(String s:sonstCodes)
        {
            if(s != null)
            {
                int pos = Arrays.asList(sonstCodes).indexOf(s);
                text.append(s+": "+sonstDiag[pos]+"\n");
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
