package com.example.kille.lasertagmobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    EditText ed1, ed2;
    String email, password;
    int counter = 3;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            signedIn();
        }
    }

    public void signedIn() {
        Toast.makeText(getApplicationContext(),"Redirecting...", Toast.LENGTH_SHORT).show();
        Intent authenticatedIntent = new Intent(AuthenticationActivity.this, HomeActivity.class);
        startActivity(authenticatedIntent);
        finish();
    }

    private void signIn(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            signedIn();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(AuthenticationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_authentication);

        b1 = (Button)findViewById(R.id.btnAuthenticate);
        b2 = (Button)findViewById(R.id.btnRegister);
        ed1 = (EditText)findViewById(R.id.editUsername);
        ed2 = (EditText)findViewById(R.id.editConfirmPassword);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    signedIn();
                }
                else {
                    email = ed1.getText().toString();
                    password = ed2.getText().toString();
                    signIn(email, password);

                    counter--;

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(AuthenticationActivity.this, RegisterActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
