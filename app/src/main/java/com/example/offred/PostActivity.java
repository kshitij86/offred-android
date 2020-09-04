package com.example.offred;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {
    EditText name, age, salary;
    Button postButton, goToGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        name = findViewById(R.id.name);
        salary = findViewById(R.id.salary);
        age = findViewById(R.id.age);
        initEditText();

        goToGet = findViewById(R.id.goToGet);
        postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(v -> {
            if(!name.getText().equals("") && !salary.getText().equals("") && !age.getText().equals("")){
                // TODO: Add support for JSON data, not only Strings, this is a mess
                String data = "{\"name\":" + name.getText() + ",\"salary\":" + salary.getText() + ",\"age\":"+ age.getText() +"}";
                Offred offred = new Offred();
                Response response = offred.post("https://dummy.restapiexample.com/api/v1/create", data);
                Toast.makeText(this, response.resBody, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Empty values not allowed !", Toast.LENGTH_SHORT).show();
            }
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