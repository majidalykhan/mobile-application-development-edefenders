package com.example.mak.madproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mGuard, mOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mGuard = (Button) findViewById(R.id.guardbtn);
        mOwner = (Button) findViewById(R.id.ownerbtn);

        mGuard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GuardActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        mOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OwnerActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
