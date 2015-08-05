package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.company.joeliomason.projectme.R;


public class SplashScreenView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ActionBar actionBar = SplashScreenView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        final Button loginButton = (Button) findViewById(R.id.login_button);
        final Button createAccountButton = (Button) findViewById(R.id.signup_button);

        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent create = new Intent(SplashScreenView.this, LoginView.class);
                startActivity(create);
            }

        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create = new Intent(SplashScreenView.this, AddAccountView.class);
                startActivity(create);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
