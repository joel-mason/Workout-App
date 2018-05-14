package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.company.joeliomason.projectme.Database.LoginDatabaseAdapter;
import com.company.joeliomason.projectme.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginEmailView extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener
        {
            private static final String TAG = "SignInActivity";
            private static final int RC_SIGN_IN = 9001;
            private TextView email, password;
            private Button loginButton;

            private FirebaseAuth mAuth;

            @Override
            protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.login_email);

                android.app.ActionBar actionBar = LoginEmailView.this.getActionBar();

                loginButton = findViewById(R.id.login_button);
                email = findViewById(R.id.emailAddress);
                password = findViewById(R.id.passwordText);

                loginButton.setOnClickListener(this);

                if (actionBar != null) {
                    actionBar.hide();
                }

                // Initialize FirebaseAuth
                mAuth = FirebaseAuth.getInstance();

            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_button:
                        signIn();
                        break;
                    default:
                        return;
                }
            }
            private void signIn() {
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    startActivity(new Intent(LoginEmailView.this, MainMenuActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginEmailView.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }



            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                // An unresolvable error has occurred and Google APIs (including Sign-In) will not
                // be available.
                Log.d(TAG, "onConnectionFailed:" + connectionResult);
                Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
            }
        }
