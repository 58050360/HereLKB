package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.os.Parcelable;
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


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText txt_email, txt_password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        findViewById(R.id.signup_action).setOnClickListener(this);
        findViewById(R.id.login_action).setOnClickListener(this);
        findViewById(R.id.forgot_pass).setOnClickListener(this);
    }

    private void userLogin() {
        final String email = txt_email.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        if (email.equals("admin@admin.admin") && password.equals("admin1")) {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful()){
                        Intent i = new Intent(LoginActivity.this,AdminActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }else {
                        Toast.makeText(getApplicationContext() , task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

        }); return; }
        if (email.isEmpty()) {
            txt_email.setError("Email is required");
            txt_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_email.setError("Please enter a valid email");
            txt_email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            txt_password.setError("Password is required");
            txt_password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            txt_password.setError("Minimum lengt of password should be 6");
            txt_password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    i.putExtra("Email",txt_email.getText().toString());
                   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext() , task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_action:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
                break;
            case R.id.forgot_pass:
                startActivity(new Intent(LoginActivity.this,PasswordActivity.class));
                finish();
                break;
            case R.id.login_action:
                userLogin();


        }
    }

}
