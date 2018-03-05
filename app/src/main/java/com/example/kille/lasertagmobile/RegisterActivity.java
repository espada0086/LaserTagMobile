package com.example.kille.lasertagmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    EditText ed1, ed2, ed3, ed4;
    private FirebaseAuth mAuth;

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    private void createUser (final String emailAddr, String confpassword, final String username) {
        mAuth.createUserWithEmailAndPassword(emailAddr, confpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username)
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent myIntent = new Intent(RegisterActivity.this, HomeActivity.class);
                                                startActivity(myIntent);
                                            }
                                        }
                                    });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed. Please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        b1 = (Button)findViewById(R.id.btnSubmit);
        b2 = (Button)findViewById(R.id.btnCancel);
        ed1 = (EditText)findViewById(R.id.editEmail);
        ed2 = (EditText)findViewById(R.id.editUsername);
        ed3 = (EditText)findViewById(R.id.editPassword);
        ed4 = (EditText)findViewById(R.id.editConfirmPassword);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailAddr = ed1.getText().toString();
                final String username = ed2.getText().toString();
                String password = ed3.getText().toString();
                String confpassword = ed4.getText().toString();
                if (!EMAIL_ADDRESS_PATTERN.matcher(emailAddr).matches()) {
                    Toast.makeText(RegisterActivity.this, "Invalid email address.",
                            Toast.LENGTH_SHORT).show();
                }else if (!password.equals(confpassword)){
                    Toast.makeText(RegisterActivity.this, "Passwords do not match. Please try again.",
                            Toast.LENGTH_SHORT).show();
                }else {
                    createUser(emailAddr, confpassword, username);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(RegisterActivity.this, AuthenticationActivity.class);
                startActivity(cancelIntent);
            }
        });
    }
}