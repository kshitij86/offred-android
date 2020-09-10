package com.example.offred;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Future;

/* GET request example */

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MAIN_ACTIVITY";
    private TextView response_box, time_box;

    // TODO: Add an EditText for URL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set values
        response_box = findViewById(R.id.request_box);
        time_box = findViewById(R.id.time_box);
        Button getButton = findViewById(R.id.button);
        Button clear = findViewById(R.id.clear);
        Button goToPost = findViewById(R.id.post);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response_box.setText("");
                time_box.setText("");
            }
        });
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // Create an object to handle IOException
                    Offred offred = new Offred();
                    Future<Response> fr =  offred.get("https://jsonplaceholder.typicode.com/todos/1");
                    Response data = fr.get();
                    if(!data.isException && data.resBody != "NULL_REQUEST"){
                        Log.d(TAG, "onClick: Call to web service successful");
                        response_box.setText(data.resBody);
                        time_box.setText("Took " + Double.toString(data.time) + "s");
                        Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    Log.e(TAG, e.getMessage());
                }
            }
        });

        goToPost.setOnClickListener(v -> {
            startActivity(new Intent(this, PostActivity.class));
        });


    }
}

