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


public class RegisterActivity extends AppCompatActivity {
    EditText name, email, phone, password;
    Button register;
    TextView login;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    TextInputLayout name_error, email_error, phone_error, pass_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

            name = (EditText) findViewById(R.id.name);
            email = (EditText) findViewById(R.id.email);
            phone = (EditText) findViewById(R.id.phone);
            password = (EditText) findViewById(R.id.password);
            login = (TextView) findViewById(R.id.login);
            register = (Button) findViewById(R.id.register);
            name_error = (TextInputLayout) findViewById(R.id.name_error);
            email_error = (TextInputLayout) findViewById(R.id.email_error);
            phone_error = (TextInputLayout) findViewById(R.id.phone_error);
            pass_error = (TextInputLayout) findViewById(R.id.pass_error);

            register.setOnClickListener(v -> SetValidation());

            login.setOnClickListener(v -> {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            });
        }

        public void SetValidation() {
            // Check for a valid name.
            if (name.getText().toString().isEmpty()) {
                name_error.setError(getResources().getString(R.string.blank_name));
                isNameValid = false;
            } else  {
                isNameValid = true;
                name_error.setErrorEnabled(false);
            }

            // Check for a valid email address.
            if (email.getText().toString().isEmpty()) {
                email_error.setError(getResources().getString(R.string.blank_email));
                isEmailValid = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                email_error.setError(getResources().getString(R.string.invalid_email));
                isEmailValid = false;
            } else  {
                isEmailValid = true;
                email_error.setErrorEnabled(false);
            }

            // Check for a valid phone number.
            if (phone.getText().toString().isEmpty()) {
                phone_error.setError(getResources().getString(R.string.blank_phone));
                isPhoneValid = false;
            } else  {
                isPhoneValid = true;
                phone_error.setErrorEnabled(false);
            }

            // Check for a valid password.
            if (password.getText().toString().isEmpty()) {
                pass_error.setError(getResources().getString(R.string.blank_password));
                isPasswordValid = false;
            } else if (password.getText().length() < 6) {
                pass_error.setError(getResources().getString(R.string.invalid_password));
                isPasswordValid = false;
            } else  {
                isPasswordValid = true;
                pass_error.setErrorEnabled(false);
            }

            if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {
                Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();

            }
        }
    }
