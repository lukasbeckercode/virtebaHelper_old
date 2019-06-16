/*
"Chirurgische Codes"-Class
TODO: IN ALL OTHER CLASSES: remove keyboard in disabled text-fields
 */

package com.example.virtebahelper;

//imports handled by IntelliJ
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

        Button backBtn = findViewById(R.id.backBtnChir); //generic "back" button
        EditText text = findViewById(R.id.editTextChir);
        text.setEnabled(false); // better design
        //local variables
        String [] chirDiag = new String[156];
        String [] chirCodes = new String[156];

        int i = 0; //counter
        for(String c:StartUpActivity.allCodes)
        {
            if(c.startsWith("2")) //2=surgical dept.
            {
                chirCodes[i] = c; //save the code to local var
                int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c); //get the position of the code in the original var
                chirDiag[i] = StartUpActivity.allDiag[pos]; //save the corresponding diagnosis the the local var
            }
            i++; //counter +1
        }

        for(String s:chirCodes) //write to text area
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
