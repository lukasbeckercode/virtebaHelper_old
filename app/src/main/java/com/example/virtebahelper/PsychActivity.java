package com.example.virtebahelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Arrays;

public class PsychActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psych);
        Button backBtn = findViewById(R.id.backBtnPsych);

        EditText text = findViewById(R.id.editTextPsych);
        text.setEnabled(false);
        String [] psychDiag = new String[156];
        String [] psychCodes = new String[156];

        int i = 0;
        for(String c:StartUpActivity.allCodes)
        {
            if(c.startsWith("43"))
            {
                psychCodes[i] = c;
                int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                psychDiag[i] = StartUpActivity.allDiag[pos];
            }
            i++;
        }


        for(String s:psychCodes)
        {
            if(s != null)
            {
                int pos = Arrays.asList(psychCodes).indexOf(s);
                text.append(s+": "+psychDiag[pos]+"\n");
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
