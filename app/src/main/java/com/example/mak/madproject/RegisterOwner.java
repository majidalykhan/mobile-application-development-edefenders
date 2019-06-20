package com.example.mak.madproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterOwner extends AppCompatActivity {


    private EditText Name, MobNumber, Email, Password;
    private Button Register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guard);


        Name = (EditText)findViewById(R.id.etname);
        MobNumber = (EditText)findViewById(R.id.etmobilenumber);
        Email = (EditText)findViewById(R.id.etemail);
        Password = (EditText)findViewById(R.id.etpassword);

        Register = (Button)findViewById(R.id.regbtn);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() !=null)
        {
            //Handle current user
        }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String mobilenumber = MobNumber.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    //We will store additional fields in database
                                    String user_id = mAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Owners").child(user_id);
                                    current_user_db.setValue(true);
                                    Toast.makeText(RegisterOwner.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(RegisterOwner.this, "Sign Up Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
