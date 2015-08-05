package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.joeliomason.projectme.Database.LoginDatabaseAdapter;
import com.company.joeliomason.projectme.R;


public class AddAccountView extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText password2;
    private EditText fName;
    private EditText sName;
    private EditText weight;
    private EditText height;

    private LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_account);

        ActionBar actionBar = AddAccountView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        //set up all of the text views
        email = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);
        fName = (EditText) findViewById(R.id.forename);
        sName = (EditText) findViewById(R.id.surname);
        weight = (EditText) findViewById(R.id.rowWeight);
        height = (EditText) findViewById(R.id.height);

        loginDatabaseAdapter = new LoginDatabaseAdapter(this);

        final Button createAccountButton = (Button) findViewById(R.id.nextButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //reset errors
                email.setError(null);
                password.setError(null);
                password2.setError(null);
                boolean cancel = false;
                View focusView = null;

                String emailStr = email.getText().toString();
                String passStr = password.getText().toString();
                String passStr2 = password2.getText().toString();
                String forename = fName.getText().toString();
                String surname = sName.getText().toString();
                String weightt = weight.getText().toString();
                String heightt = height.getText().toString();

                if(TextUtils.isEmpty(passStr)) {
                    password.setError("This field is required");
                    focusView = password;
                    cancel = true;
                }
                if(TextUtils.isEmpty(passStr2)) {
                    password2.setError("This field is required");
                    focusView = password2;
                    cancel = true;
                }
                if (!isPasswordValid(passStr)) {
                    password.setError("This password is too short");
                    focusView = password;
                    cancel = true;
                }
                if (!isPasswordValid(passStr2)) {
                    password2.setError("This password is too short");
                    focusView = password2;
                    cancel = true;
                }
                if (!passStr.equals(passStr2)) {
                    password.setError("These passwords don't match");
                    password2.setError("These passwords don't match");
                    focusView = password2;
                    cancel = true;
                }

                // Check for a valid email address.
                if (TextUtils.isEmpty(emailStr)) {
                    email.setError("This field is required");
                    focusView = email;
                    cancel = true;
                } else if (!isEmailValid(emailStr)) {
                    email.setError("This email address is invalid");
                    focusView = email;
                    cancel = true;
                } else if (loginDatabaseAdapter.alreadyExists(emailStr)) {
                    email.setError("This email already exists");
                    focusView = email;
                    cancel = true;
                }

                //check if all the other texts have been filled out

                if(TextUtils.isEmpty(forename)) {
                    fName.setError("This field is required");
                    focusView = fName;
                    cancel = true;
                } else if(TextUtils.isEmpty(surname)) {
                    sName.setError("This field is required");
                    focusView = sName;
                    cancel = true;
                } else if(TextUtils.isEmpty(weightt)) {
                    weight.setError("This field is required");
                    focusView = weight;
                    cancel = true;
                } else if(TextUtils.isEmpty(heightt)) {
                    height.setError("This field is required");
                    focusView = height;
                    cancel = true;
                }

                //if cancel is true, dont allow the login
                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else  if (!cancel) {
                    loginDatabaseAdapter.insertEntry(emailStr, passStr, forename, surname,
                            Integer.parseInt(weight.getText().toString()), Integer.parseInt(height.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Account Successfully Created " + emailStr + " " + passStr, Toast.LENGTH_LONG).show();
                    Intent create = new Intent(AddAccountView.this, SplashScreenView.class);
                    startActivity(create);
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean isEmailValid(String email) {
        return email.length() > 4;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
