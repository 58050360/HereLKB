package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText txt_signemail, txt_signpassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txt_signemail = (EditText) findViewById(R.id.txt_signemail);
        txt_signpassword = (EditText) findViewById(R.id.txt_signpassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.gosignup_action).setOnClickListener(this);
        findViewById(R.id.gologin_action).setOnClickListener(this);

    }
    private void registerUser(){
        String email = txt_signemail.getText().toString().trim();
        String password = txt_signpassword.getText().toString().trim();
        if(email.isEmpty()){
            txt_signemail.setError("Email is required");
            txt_signemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txt_signemail.setError("Please enter a valid email");
            txt_signemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            txt_signpassword.setError("Password is required");
            txt_signpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            txt_signpassword.setError("Minimum lengt of password should be 6");
            txt_signpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
       mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               progressBar.setVisibility(View.GONE);
               if(task.isSuccessful()) {
                   Toast.makeText(getApplicationContext(),"User Register Successful",Toast.LENGTH_SHORT).show();
               } else { Toast.makeText(getApplicationContext(),"Some Error occured",Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gosignup_action:
                registerUser();
                break;
            case R.id.gologin_action:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
