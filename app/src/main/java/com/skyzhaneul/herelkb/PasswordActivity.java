package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {
    private EditText edt_email;
    private Button reset_action;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        edt_email = (EditText) findViewById(R.id.edt_email);
        reset_action = (Button) findViewById(R.id.reset_action);
        firebaseAuth = FirebaseAuth.getInstance();

        reset_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = edt_email.getText().toString().trim();
                if (useremail.equals("")){
                    Toast.makeText(PasswordActivity.this,"Please enter your registered email ID", Toast.LENGTH_SHORT).show();
                }else { firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(PasswordActivity.this,"Password reset email sent!",Toast.LENGTH_SHORT).show();
                            finish();
                         startActivity(new Intent(PasswordActivity.this,LoginActivity.class));
                        } else {
                            Toast.makeText(PasswordActivity.this,"Error in sending password email reset!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });}
            }
        });

    }
}
