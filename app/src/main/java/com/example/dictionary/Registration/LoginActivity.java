package com.example.dictionary.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dictionary.Main.MainActivity;
import com.example.dictionary.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email,password; Button loginBtn; ProgressBar progressBar;
    FirebaseAuth mAuth;
    ImageView logImage;
    TextView singUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        singUp = findViewById(R.id.btn_sigh_up);
        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        logImage = findViewById(R.id.logImage);

        email = findViewById(R.id.logEmail);
        password = findViewById(R.id.logPassword);
        loginBtn = findViewById(R.id.logButton);
        progressBar = findViewById(R.id.progressBar);




        logImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                loginBtn.setVisibility(View.INVISIBLE);
                final String userLogEmail = email.getText().toString();
                final String userLogPassword = password.getText().toString();


                if (userLogEmail.isEmpty() || userLogPassword.isEmpty()){
                    showMessage("Please verify all fields");
                }else{
                    signIn(userLogEmail,userLogPassword);
                }
            }
        });

    }

    private void signIn(String userLogEmail, String userLogPassword) {
        mAuth.signInWithEmailAndPassword(userLogEmail,userLogPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);
                    updateUI();
                }else {
                    showMessage(task.getException().getMessage());
                    progressBar.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    private void updateUI() {
        startActivity( new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            updateUI();
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}