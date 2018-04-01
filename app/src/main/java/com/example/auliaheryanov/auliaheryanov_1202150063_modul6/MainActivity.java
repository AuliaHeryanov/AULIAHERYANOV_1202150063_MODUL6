package com.example.auliaheryanov.auliaheryanov_1202150063_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity {

    private EditText mEmailLogin, mPassLogin;
    private Button mLoginButton, mRegisterButton;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailLogin = findViewById(R.id.emailLogin);
        mPassLogin = findViewById(R.id.passLogin);
        mLoginButton = findViewById(R.id.loginButton);
        mRegisterButton = findViewById(R.id.registerButton);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


    }
    public void register(View view) {
        String email = mEmailLogin.getText().toString();
        String password = mPassLogin.getText().toString();


        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Please input email and password", Toast.LENGTH_SHORT).show();
        } else {
            if (password.length()>=8){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    Toast.makeText(MainActivity.this, "Register failed",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }else{
                Toast.makeText(MainActivity.this, "Password must be 8 or more characters", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void login(View view) {
        String email = mEmailLogin.getText().toString();
        String password = mPassLogin.getText().toString();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Please input email and password", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(MainActivity.this, MainMenu.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Login failed",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
}
