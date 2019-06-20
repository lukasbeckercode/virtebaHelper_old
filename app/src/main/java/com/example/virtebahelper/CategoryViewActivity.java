/*
"Chirurgische Codes"-Class
 */

package com.example.virtebahelper;

//imports handled by IntelliJ
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Arrays;

public class CategoryViewActivity extends AppCompatActivity {
    //local variables
    String [] chirDiag = new String[156];
    String [] chirCodes = new String[156];
    int kat_local = StartUpActivity.kat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryview);

        Button backBtn = findViewById(R.id.backBtnChir); //generic "back" button
        EditText text = findViewById(R.id.editTextChir);
        text.setEnabled(false); // better design

        getCodes(kat_local);

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

    private void getCodes(int kat){


        int i = 0; //counter
        for(String c:StartUpActivity.allCodes)
        {
            switch (kat)
            {
                case 1:
                    if(c.startsWith("2")) //2=surgical dept.
                    {
                        chirCodes[i] = c; //save the code to local var
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c); //get the position of the code in the original var
                        chirDiag[i] = StartUpActivity.allDiag[pos]; //save the corresponding diagnosis the the local var
                    }
                    break;
                case 2:
                    if(c.startsWith("3")) //3=innere medizin
                    {
                        chirCodes[i] = c;
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                        chirDiag[i] = StartUpActivity.allDiag[pos];
                    }
                    break;
                case 3:
                    if(c.startsWith("41") || c.startsWith("42")) //41=neuro; 42=insult
                    {
                        chirCodes[i] = c;
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                        chirDiag[i] = StartUpActivity.allDiag[pos];
                    }
                    break;
                case 4:
                    if(c.startsWith("43")) //43=Psych
                    {
                        chirCodes[i] = c;
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                        chirDiag[i] = StartUpActivity.allDiag[pos];
                    }
                    break;
                case 5:
                    if(c.startsWith("5")) //5=gyn & children
                    {
                        chirCodes[i] = c;
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                        chirDiag[i] = StartUpActivity.allDiag[pos];
                    }
                    break;
                case 6:
                    if(c.startsWith("1") || c.startsWith("7")) //1=CPR; 7=Misc. Codes
                    {
                        chirCodes[i] = c; //save the code to the array
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c); //get the position of the code within the original array
                        chirDiag[i] = StartUpActivity.allDiag[pos]; //get the corresponding diagnosis
                    }
                    break;
            }

            i++; //counter +1
        }

    }
}
