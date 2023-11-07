package com.example.fbauth_albert;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class logAndSignResult extends AppCompatActivity {

    Intent gi;

    TextView welcomeMsg;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_result);
        gi = getIntent();
        welcomeMsg = findViewById(R.id.textView);
        welcomeMsg.setText("Welcome, " + gi.getStringExtra("name") + "!"); // welcome the user
    }

    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    @Override
    protected void onDestroy () {
        FirebaseAuth.getInstance().signOut();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        finish();
        super.onBackPressed();
    }
}