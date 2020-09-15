package com.example.offred;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Future;

public class PostActivity extends AppCompatActivity {
    private final String TAG = "POST_ACTIVITY";
    private final String demoURL = "https://dummy.restapiexample.com/api/v1/create";
    private TextView time_box;
    private EditText name, age, salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        name = findViewById(R.id.name);
        salary = findViewById(R.id.salary);
        age = findViewById(R.id.age);
        time_box = findViewById(R.id.time_box);
        Button clear = findViewById(R.id.clear);
        initEditText();

        Button goToGet = findViewById(R.id.goToGet);
        Button postButton = findViewById(R.id.postButton);
        TextView urlBox = findViewById(R.id.urlBox);

        urlBox.setText(demoURL);

        postButton.setOnClickListener(v -> {
            try{
                if(!name.getText().toString().equals("") && !salary.getText().toString().equals("") && !age.getText().toString().equals("")){
                    // TODO: Add support for JSON data, not only Strings, this is a mess
                    String passJSON = "{\"name\":" + name.getText() + ",\"salary\":" + salary.getText() + ",\"age\":"+ age.getText() +"}";
                    Offred offred = new Offred();
                    Future<Response> fr = offred.post(demoURL, passJSON);
                    Response data = fr.get();
                    Toast.makeText(this, data.resBody.toString(), Toast.LENGTH_SHORT).show();
                    time_box.setText("Took " + Double.toString(data.time) + "s");
                } else {
                    Toast.makeText(this, "Empty values not allowed !", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
        });

        clear.setOnClickListener(v -> {
            name.setText("");
            age.setText("");
            salary.setText("");
        });

        goToGet.setOnClickListener(v -> {
            finish();
        });
    }

    private void initEditText(){
        name.setText("John Doe");
        salary.setText("1000");
        age.setText("45");
    }
}