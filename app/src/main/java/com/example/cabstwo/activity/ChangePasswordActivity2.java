package com.example.cabstwo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cabstwo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class  ChangePasswordActivity2 extends AppCompatActivity
{
    private static final String TAG = ChangePasswordActivity2.class.getName();
    private FirebaseUser user;
    EditText email, emailEdit,pass,repass;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password2);
        emailEdit=findViewById(R.id.emailChange);
//        pass=findViewById(R.id.passChange);
//        repass=findViewById(R.id.passChange1);
        submit=findViewById(R.id.btnChangePass);
        user = FirebaseAuth.getInstance().getCurrentUser();
       final String email = user.getEmail();
       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               FirebaseAuth.getInstance().sendPasswordResetEmail(emailEdit.getText().toString())
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if (task.isSuccessful()) {
                                   Log.d(TAG, "Email sent.");
                                   Toast.makeText(ChangePasswordActivity2.this,"Email sent successful..",Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
               finish();
           }
       });
       //AuthCredential credential = EmailAuthProvider.getCredential(email.toString(),pass.toString());

        /*
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    user.updatePassword(pass.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Something went wrong. Please try again later",Toast.LENGTH_SHORT).show();
                            }else {
                                submit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(ChangePasswordActivity2.this,Login.class));
                                    }
                                });
                                Toast.makeText(getApplicationContext(),"Password Successfully Update",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"Authentication Failed..",Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}

