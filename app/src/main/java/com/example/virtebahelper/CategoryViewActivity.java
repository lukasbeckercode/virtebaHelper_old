package com.example.virtebahelper;

//imports handled by IntelliJ

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <title>CategoryViewActivity </title>
 * <summary>This view adapts to the selected Category and
 * displays the according Code:Diagnosis pairs</summary>
 *
 * @author lukasbecker
 */
public class CategoryViewActivity extends AppCompatActivity {

    private List<String> allCodes = new ArrayList<>();
    TreeMap<Integer, String> codes = new TreeMap<>(); //TreeMap automatically sorts by key
    private final int kat_local = StartUpActivity.kat;

    /**
     * gets executed each time this class is called
     * @param savedInstanceState android native param
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CodeHandler codeHandler = new CodeHandler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryview);

        Button backBtn = findViewById(R.id.backBtnChir); //generic "back" button
        EditText text = findViewById(R.id.editTextChir);
        text.setEnabled(false); // better design

        allCodes = codeHandler.getAllCodes();
        getCodes(kat_local,codeHandler); // call the getCodes method and pass an int to tell it which codes we want

        for (Map.Entry<Integer, String> entry : codes.entrySet()) {
            @SuppressLint("DefaultLocale") String s = String.format("%d: %s%n", entry.getKey(), entry.getValue());
            text.append(s);
        }

        backBtn.setOnClickListener(v -> goBack());
    }

    /**
     * to go back to the previous Activity
     */
    private void goBack() {
        Intent goBackIntent = new Intent(this, StartUpActivity.class);
        startActivity(goBackIntent);
    }

    /**
     * puts pzc and diagnosis as Key an Value in a TreeList
     * @param c the code that is added
     * @param handler CodeHandler Object to retrieve necessary data
     */
    private void matchCodes(String c, CodeHandler handler) {

        int pos = allCodes.indexOf(c); //get the position of the code in the original var
        codes.put(handler.getCode(pos), handler.getDiag(pos));
    }

    /**
     * gets the codes for the wanted Category
     * @param kat the Category a user selected
     * @param codeHandler CodeHandler Object to retrieve necessary data
     */
    private void getCodes(int kat,CodeHandler codeHandler) { //getCodes method, need an int to tell it which codes to display

        for (int i = 0; i < codeHandler.getListLen(); i++) {

            String c = allCodes.get(i);
            switch (kat) //which category is wanted
            {
                case 1: //surgical codes
                    if (c.startsWith("2")) //2=surgical dept.
                    {
                        matchCodes(c, codeHandler);
                    }
                    break;
                case 2: //internal codes
                    if (c.startsWith("3")) //3=innere medizin
                    {
                        matchCodes(c, codeHandler);
                    }
                    break;
                case 3: //neurological codes
                    if (c.startsWith("41") || c.startsWith("42")) //41=neuro; 42=insult
                    {
                        matchCodes(c, codeHandler);
                    }
                    break;
                case 4: //psych codes
                    if (c.startsWith("43")) //43=Psych
                    {
                        matchCodes(c, codeHandler);
                    }
                    break;
                case 5: //gyn/children
                    if (c.startsWith("5")) //5=gyn & children
                    {
                        matchCodes(c, codeHandler);
                    }
                    break;
                case 6: //misc. codes
                    if (c.startsWith("1") || c.startsWith("7")) //1=CPR; 7=Misc. Codes
                    {
                        matchCodes(c, codeHandler);
                    }
                    break;
            }
        }

    }
}
