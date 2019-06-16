package com.example.virtebahelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Arrays;

public class ChirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chir);

        Button backBtn = findViewById(R.id.backBtnChir);
        EditText text = findViewById(R.id.editTextChir);
        text.setEnabled(false);
        String [] chirDiag = new String[156];
        String [] chirCodes = new String[156];

        int i = 0;
        for(String c:StartUpActivity.allCodes)
        {
            if(c.startsWith("2"))
            {
                chirCodes[i] = c;
                int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                chirDiag[i] = StartUpActivity.allDiag[pos];
            }
            i++;
        }

        for(String s:chirCodes)
        {
            if(s != null)
            {
                int pos = Arrays.asList(chirCodes).indexOf(s);
                text.append(s+": "+chirDiag[pos]+"\n");
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
