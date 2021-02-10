package com.example.cabstwo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cabstwo.R;
import com.example.cabstwo.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_upActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText usrname, usrpass, confpass, userEmail, cityLiveIn;

    private DatabaseReference databaseReference;
    Button singUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usrname = findViewById(R.id.usrname);
        userEmail = findViewById(R.id.useremail);
        usrpass = findViewById(R.id.userpass);
        confpass = findViewById(R.id.userconf);
        singUpBtn = findViewById(R.id.sign_upBtn);
        cityLiveIn = findViewById(R.id.usercity);

        //......FirebaseAuth............
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usrname.getText() != null && userEmail.getText() != null && cityLiveIn.getText() != null){
                    String userName = usrname.getText().toString();
                    String email = userEmail.getText().toString();
                    String city = cityLiveIn.getText().toString();

                    mAuth.createUserWithEmailAndPassword(email, usrpass.getText().toString())
                            .addOnCompleteListener(Sign_upActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Sign_upActivity.this, "Register Successully", Toast.LENGTH_SHORT).show();
                                        Users users = new Users(userName, email, city);
                                        databaseReference.child("users").child(userName).setValue(users);
                                        startActivity(new Intent(getApplicationContext(), Login.class));
                                        finish();
                                    } else {
                                        Toast.makeText(Sign_upActivity.this, task.getException() + "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });

                }

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // reload();
        }
    }
}