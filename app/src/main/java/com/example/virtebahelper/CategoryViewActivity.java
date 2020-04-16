/*
Class in which the Categories of codes are displayed
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

    private final String [] catDiag = new String[158];
    private final String [] catCodes = new String[158];
    private final int kat_local = StartUpActivity.kat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryview);

        Button backBtn = findViewById(R.id.backBtnChir); //generic "back" button
        EditText text = findViewById(R.id.editTextChir);
        text.setEnabled(false); // better design

        getCodes(kat_local); // call the getCodes method and pass an int to tell it which codes we want

        for(String s:catCodes) //write to text area
        {
            if(s != null)
            {
                int pos = Arrays.asList(catCodes).indexOf(s);
                text.append(s+": "+catDiag[pos]+"\n");
            }

        }

        backBtn.setOnClickListener(v->goBack());
    }

    private void goBack()
    {
        Intent goBackIntent = new Intent(this,StartUpActivity.class);
        startActivity(goBackIntent);
    }

    private void getCodes(int kat){ //getCodes method, need an int to tell it which codes to display


        int i = 0; //counter
        for(String c:StartUpActivity.allCodes)
        {
            switch (kat) //which category is wanted
            {
                case 1: //surgical codes
                    if(c.startsWith("2")) //2=surgical dept.
                    {
                        catCodes[i] = c; //save the code to local var
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c); //get the position of the code in the original var
                        catDiag[i] = StartUpActivity.allDiag[pos]; //save the corresponding diagnosis the the local var
                    }
                    break;
                case 2: //internal codes
                    if(c.startsWith("3")) //3=innere medizin
                    {
                        catCodes[i] = c;
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                        catDiag[i] = StartUpActivity.allDiag[pos];
                    }
                    break;
                case 3: //neurological codes
                    if(c.startsWith("41") || c.startsWith("42")) //41=neuro; 42=insult
                    {
                        catCodes[i] = c;
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                        catDiag[i] = StartUpActivity.allDiag[pos];
                    }
                    break;
                case 4: //psych codes
                    if(c.startsWith("43")) //43=Psych
                    {
                        catCodes[i] = c;
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                        catDiag[i] = StartUpActivity.allDiag[pos];
                    }
                    break;
                case 5: //gyn/children
                    if(c.startsWith("5")) //5=gyn & children
                    {
                        catCodes[i] = c;
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c);
                        catDiag[i] = StartUpActivity.allDiag[pos];
                    }
                    break;
                case 6: //misc. codes
                    if(c.startsWith("1") || c.startsWith("7")) //1=CPR; 7=Misc. Codes
                    {
                        catCodes[i] = c; //save the code to the array
                        int pos = Arrays.asList(StartUpActivity.allCodes).indexOf(c); //get the position of the code within the original array
                        catDiag[i] = StartUpActivity.allDiag[pos]; //get the corresponding diagnosis
                    }
                    break;
            }

            i++; //counter +1
        }

    }
}
