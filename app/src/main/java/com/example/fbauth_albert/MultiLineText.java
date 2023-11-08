package com.example.fbauth_albert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    FirebaseDatabase db;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_line_text);
        input_text = findViewById(R.id.input_text);
        output = findViewById(R.id.view_text);
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("text");
    }


    public void save(View view) {
        String text = input_text.getText().toString();
        myRef.setValue(text);
    }
}