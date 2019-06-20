package com.example.mak.madproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class LoginGuard extends AppCompatActivity {

    private EditText Email, Password;
    private Button Login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guard);


        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                if(user !=null)
                {
                    Intent intent = new Intent(LoginGuard.this, GuardMapsActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        Email = (EditText)findViewById(R.id.etemail);
        Password = (EditText)findViewById(R.id.etpassword);
        Login = (Button) findViewById(R.id.loginbtn);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginGuard.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()) {
                            Toast.makeText(LoginGuard.this, "Sign In Error", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent intent = new Intent(LoginGuard.this, GuardMapsActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }
                });
            }
        });


    }
}
