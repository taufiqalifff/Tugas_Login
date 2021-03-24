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

public class MainActivity extends AppCompatActivity {
    
    EditText email,password;
    Button signupbtn;
    TextView loginHref;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        signupbtn = findViewById(R.id.signupBtn);
        loginHref = findViewById(R.id.loginHref);
        firebaseAuth = FirebaseAuth.getInstance();
        
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailstr = email.getText().toString();
                String passwordstr = password.getText().toString();
                
                if (emailstr.isEmpty() || passwordstr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Isi yang kosong", Toast.LENGTH_SHORT).show();
                } else if (!(emailstr.isEmpty() && passwordstr.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(emailstr, passwordstr).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "gagal mendaftar", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, Login_Activity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginHref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(MainActivity.this, Login_Activity.class);
                startActivity(toLogin);
            }
        });
    }
}