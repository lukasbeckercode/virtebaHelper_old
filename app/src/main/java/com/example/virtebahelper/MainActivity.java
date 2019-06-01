package com.example.virtebahelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView1);
        textView.setMovementMethod(new ScrollingMovementMethod());

        String data = "";
        StringBuffer sbuffer = new StringBuffer();

        InputStream stream = this.getResources().openRawResource(R.raw.pzc);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        if(stream!=null){
            try {
                while ((data=reader.readLine())!=null){
                    sbuffer.append(data+"\n");
                }
                textView.setText(sbuffer);
                stream.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
