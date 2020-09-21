package com.example.textinput;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputEditText emailInput;
    TextInputEditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        setUpListeners();
        setUpActionBar();
    }

    private void bindViews()
    {
        emailInput = findViewById(R.id.textInputEditText_email);
        passwordInput = findViewById(R.id.textInputEditText_password);
    }

    private void setUpActionBar()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void OnCLickLogin(View view)
    {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

       if(validateInput(email, password)) {
           Toast.makeText(this, email + " " + password, Toast.LENGTH_SHORT).show();
       }
    }

    private boolean validateInput(String email, String password)
    {
        if(email.equals(""))
        {
            TextInputLayout emailLayout = findViewById(R.id.textInputLayout);
            emailLayout.setError("Please enter valid email id");
            emailLayout.setErrorEnabled(true);
            return false;
        }

        if(password.equals(""))
        {
            TextInputLayout passwordLayout = findViewById(R.id.textInputLayout2);
            passwordLayout.setError("Please enter valid password");
            passwordLayout.setErrorEnabled(true);
            return false;
        }

        if(password.length() < 8)
        {
            TextInputLayout passwordLayout = findViewById(R.id.textInputLayout2);
            passwordLayout.setError("password is too short");
            passwordLayout.setErrorEnabled(true);
            return false;
        }

        return true;
    }

    private void setUpListeners()
    {
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                TextInputLayout emailLayout = findViewById(R.id.textInputLayout);
                Log.i("myTag", charSequence.toString());

                if(charSequence.toString().equals(""))
                {
                    emailLayout.setError("Please enter valid email id");
                    emailLayout.setErrorEnabled(true);
                }
                else
                {
                    emailLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {

            TextInputLayout passwordLayout = findViewById(R.id.textInputLayout2);
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int i1, int i2) {

                if(passwordInput.length() < 8)
                {
                    passwordLayout.setError("password is too short");
                    passwordLayout.setErrorEnabled(true);
                }
                else
                {
                    passwordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}