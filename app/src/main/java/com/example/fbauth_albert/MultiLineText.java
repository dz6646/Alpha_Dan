package com.example.fbauth_albert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MultiLineText extends AppCompatActivity {

    EditText input_text;
    TextView output;
    FirebaseDatabase database;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_line_text);
        input_text = findViewById(R.id.input_text);
        output = findViewById(R.id.view_text);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();
    }

    public void save_text(View view) {
        String text = input_text.getText().toString();

        if(text.length() > 0)
        {
            //save
            dbRef.setValue(text);
        }

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                output.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MultiLineText.this, "Failed to load", Toast.LENGTH_SHORT).show();
            }
        });

    }
}