package com.example.dell.mapapp;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Start extends AppCompatActivity {
    EditText user,pass,id;
    Button sub;
    String username,password,identity;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mAuth = FirebaseAuth.getInstance();
        user = (EditText) findViewById(R.id.userid);
        pass = (EditText) findViewById(R.id.passid);
        id = (EditText) findViewById(R.id.identity);
        sub = (Button) findViewById(R.id.submit);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user.getText().toString();
                password = pass.getText().toString();
                identity = id.getText().toString();
                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(Start.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Start.this,identity,Toast.LENGTH_LONG).show();
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent i = new Intent(Start.this,MapActivity.class);
                                    i.putExtra("key",identity);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Start.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });

    }
}
