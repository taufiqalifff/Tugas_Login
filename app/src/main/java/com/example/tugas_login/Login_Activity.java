package com.example.tugas_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity {

    EditText email,password;
    Button loginbtn;
    TextView signupHref;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        loginbtn = findViewById(R.id.loginBtn);
        signupHref = findViewById(R.id.signUpHref);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Intent i = new Intent(Login_Activity.this, Home_Activity.class);
                    startActivity(i);
                }
            }
        };


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailstr = email.getText().toString();
                String passwordstr = password.getText().toString();

                if (emailstr.isEmpty() || passwordstr.isEmpty()) {
                    Toast.makeText(Login_Activity.this, "Isi yang kosong", Toast.LENGTH_SHORT).show();
                } else if (!(emailstr.isEmpty() && passwordstr.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(emailstr, passwordstr).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(Login_Activity.this, "gagal mendaftar", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Login_Activity.this, Home_Activity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Login_Activity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signupHref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(Login_Activity.this, Home_Activity.class);
                startActivity(toLogin);
            }
        });
    }
}