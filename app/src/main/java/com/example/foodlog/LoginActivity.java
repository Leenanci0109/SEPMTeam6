package com.example.foodlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView register;
    boolean email_valid, password_valid;
    TextInputLayout email_error, pass_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        email_error = (TextInputLayout) findViewById(R.id.email_error);
        pass_error = (TextInputLayout) findViewById(R.id.pass_error);

        login.setOnClickListener(v -> {
            SetValidation();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
                });

        register.setOnClickListener(v -> {
            // redirect to RegisterActivity
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }

    public void SetValidation() {
        if (email.getText().toString().isEmpty()) {
            email_error.setError(getResources().getString(R.string.blank_email));
            email_valid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email_error.setError(getResources().getString(R.string.invalid_email));
            email_valid = false;
        } else {
            email_valid = true;
            email_error.setErrorEnabled(false);
        }

        if (password.getText().toString().isEmpty()) {
            pass_error.setError(getResources().getString(R.string.blank_password));
            password_valid = false;
        } else if (password.getText().length() < 6) {
            pass_error.setError(getResources().getString(R.string.invalid_password));
            password_valid = false;
        } else {
            password_valid = true;
            pass_error.setErrorEnabled(false);
        }

        if (email_valid && password_valid) {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}