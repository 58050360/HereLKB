package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        txt_email = (EditText) findViewById(R.id.txt_email);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.reset_action).setOnClickListener(this);
        findViewById(R.id.back_action).setOnClickListener(this);
    }

    private void resetPass() {
        final String email = txt_email.getText().toString().trim();
        if (email.isEmpty()) {
            txt_email.setError("Email is required");
            txt_email.requestFocus();
            return;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt_email.setError("Please enter a valid email");
            txt_email.requestFocus();
            return;
        } else { mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Password reset email sent!",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(PasswordActivity.this,LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Error in sending password email reset!",Toast.LENGTH_SHORT).show();

                }
            }
        }); }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_action:
                resetPass();
                break;
            case R.id.back_action:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
