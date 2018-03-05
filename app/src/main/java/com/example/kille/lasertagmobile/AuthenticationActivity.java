package com.example.kille.lasertagmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    EditText ed1, ed2;
    int counter = 3;
    private FirebaseAuth mAuth;

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
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent authenticatedIntent = new Intent(AuthenticationActivity.this, HomeActivity.class);
                    startActivity(authenticatedIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong " +
                            "Credentials",Toast.LENGTH_SHORT).show();

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
