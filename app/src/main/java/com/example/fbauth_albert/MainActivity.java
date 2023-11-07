package com.example.fbauth_albert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Intent si;
    EditText email;
    EditText password;
    FirebaseAuth mAuth;
    int nameEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        si = new Intent(this, MainActivity2.class);
    }

    @Override
    protected void onStart() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "there is a user logged in", Toast.LENGTH_SHORT).show();
            startActivity(si);
        }
        super.onStart();
    }

    public void sign_in(View view) {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                si.putExtra("name", email.getText().toString().substring(0, email.getText().toString().indexOf('@')));
                Toast.makeText(MainActivity.this, "Sign up Successful", Toast.LENGTH_SHORT).show();

                startActivity(si);
            }
            else {
                Toast.makeText(MainActivity.this, "Sign up Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void log_in(View view) {
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    si.putExtra("name", email.getText().toString().substring(0, email.getText().toString().indexOf('@')));
                    Toast.makeText(MainActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();

                    startActivity(si);
                }
                else {
                    Toast.makeText(MainActivity.this, "log in Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
