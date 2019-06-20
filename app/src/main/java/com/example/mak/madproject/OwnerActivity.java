package com.example.mak.madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerActivity extends AppCompatActivity {

    private Button Signin, Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard);

        Signin = (Button) findViewById(R.id.loginbtn);
        Signup = (Button) findViewById(R.id.registerbtn);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerActivity.this, LoginOwner.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerActivity.this, RegisterOwner.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }
}
