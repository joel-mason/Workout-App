package com.company.joeliomason.projectme.Views;

/**
 * Created by JoelioMason on 21/02/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.joeliomason.projectme.Database.LoginDatabaseAdapter;
import com.company.joeliomason.projectme.R;

public class LoginView extends AppCompatActivity
{
    Button btnSignIn;
    LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ActionBar actionBar = LoginView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        // create an instance of SQLite Database
        loginDatabaseAdapter =new LoginDatabaseAdapter(this);

        // Get The Reference Of Buttons
        btnSignIn=(Button)findViewById(R.id.login_button);
        btnSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                signIn();
            }

        });
    }


    // Methods to handleClick Event of Sign In Button
    public void signIn()
    {

        // get the References of views
        final  EditText editTextUserName=(EditText) findViewById(R.id.emailAddress);
        final  EditText editTextPassword=(EditText) findViewById(R.id.passwordText);
        //reset errors
        editTextUserName.setError(null);
        editTextPassword.setError(null);
        boolean cancel = false;
        View focusView = null;
        // get The User name and Password
        String email=editTextUserName.getText().toString();
        String password=editTextPassword.getText().toString();

        // fetch the Password form database for respective user name
        String storedPassword= loginDatabaseAdapter.getPassword(email);

        // Check for a valid password or if the user entered one.
        if(TextUtils.isEmpty(password)) {
            editTextUserName.setError("This field is required");
            focusView = editTextUserName;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            editTextPassword.setError("This password is too short");
            focusView = editTextPassword;
            cancel = true;
        }

        // Check for a valid email address or if the user entered one.
        if (TextUtils.isEmpty(email)) {
            editTextUserName.setError("This field is required");
            focusView = editTextUserName;
            cancel = true;
            Toast.makeText(LoginView.this, "password empty", Toast.LENGTH_LONG).show();
        } else if (!isEmailValid(email)) {
            editTextUserName.setError("This email doesn't exist");
            focusView = editTextUserName;
            cancel = true;

        }
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            editTextUserName.setError("This field is required");
            editTextPassword.setError("This field is required");
            focusView = editTextUserName;
            cancel = true;

        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }

        // check if the Stored password matches with  Password entered by user
        if(password.equals(storedPassword) && !cancel)
        {
            Toast.makeText(LoginView.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
            Intent create = new Intent(LoginView.this, MainMenuView2.class);
            startActivity(create);
        }
        else
        {
            editTextPassword.setError("This password is incorrect");
            editTextPassword.requestFocus();
            Toast.makeText(LoginView.this, "doesnt match " + storedPassword, Toast.LENGTH_LONG).show();
        }

    }

    private boolean isEmailValid(String email) {
        return email.length() > 4;
    }

    private boolean isPasswordValid(String password) {
        //need to add numbers too
        return password.length() > 4;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
