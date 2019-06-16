package com.example.virtebahelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Arrays;

public class InternActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern);
        Button backBtn = findViewById(R.id.backBtnIntern);

        EditText text = findViewById(R.id.editTextIntern);
        text.setEnabled(false);
        String [] InternDiag = new String[156];
        String [] InternCodes = new String[156];

        int i = 0;
        for(String c:StartUpActivity.allCodes)
        {
            if(c.startsWith("3"))
            {
                InternCodes[i] = c;
                int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                InternDiag[i] = StartUpActivity.allDiag[pos];
            }
            i++;
        }


        for(String s:InternCodes)
        {
            if(s != null)
            {
                    int pos = Arrays.asList(InternCodes).indexOf(s);
                    text.append(s+": "+InternDiag[pos]+"\n");

        }

        }


        backBtn.setOnClickListener(v->goBack());
    }

    private void goBack()
    {
        Intent goBackIntent = new Intent(this,StartUpActivity.class);
        startActivity(goBackIntent);
    }
}
