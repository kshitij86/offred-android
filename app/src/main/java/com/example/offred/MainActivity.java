package com.example.offred;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/* GET request example */

public class MainActivity extends AppCompatActivity {
    private TextView response_box, time_box;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        response_box = findViewById(R.id.request_box);
        time_box = findViewById(R.id.time_box);
        Button btn = findViewById(R.id.button);
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response_box.setText("");
                time_box.setText("");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Response data = Offred.get("https://jsonplaceholder.typicode.com/todos/1");
                    if(!data.isException){
                        Log.d(TAG, "onClick: Call to web service successful");
                        response_box.setText(data.resBody);
                        time_box.setText("Took " + Double.toString(data.time) + "s");
                        Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(getBaseContext(), "Can't connect. Are you online?", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

