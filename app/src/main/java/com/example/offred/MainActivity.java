package com.example.offred;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.request_box);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    tv.setText("");
                    tv.setText(Offred.giveResponse("https://jsonplaceholder.typicode.com/todos/1"));
                    Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    Log.e(getApplication().getPackageName(), e.getMessage());
                    Toast.makeText(getBaseContext(), "Can't connect. Are you online?", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

