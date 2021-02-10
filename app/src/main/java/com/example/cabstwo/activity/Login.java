package com.example.cabstwo.activity;

import com.example.cabstwo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class Login extends AppCompatActivity {
    TextInputEditText email,pass;
    private View parent_view;
    TextView new_user,forgot;
    Button login;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth       = FirebaseAuth.getInstance();
        parent_view = findViewById(android.R.id.content);
        login       = findViewById(R.id.loginbtn);
        email       = findViewById(R.id.emailtxt);
        pass        = findViewById(R.id.txtpass);
        new_user    = findViewById(R.id.sign_up);
        forgot     =findViewById(R.id.forgot_password);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ChangePasswordActivity2.class));
            }
        });

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Sign_upActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = Objects.requireNonNull(email.getText()).toString();
                String password1 = Objects.requireNonNull(pass.getText()).toString().trim();

                databaseReference = FirebaseDatabase.getInstance().getReference();

                // firebase sign in
                mAuth.signInWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Login.this, "Welcome back" + user.getEmail(), Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    finish();

                                } else {
                                    Toast.makeText(Login.this, "Please Register First..." , Toast.LENGTH_LONG).show();
                                }
                                // ...
                            }
                        });
//                if (TextUtils.isEmpty(email1)&&(TextUtils.isEmpty(password1)))
//               {
//                   Toast.makeText(getApplicationContext(),"Fill All Fields",Toast.LENGTH_SHORT).show();
//               }
//                else
//                {
//                    startActivity(new Intent(Login.this, MainActivity.class));
//                    finish();
//                }
            }
        });


    }
}
