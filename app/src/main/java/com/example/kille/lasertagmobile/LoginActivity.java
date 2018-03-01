package com.example.kille.lasertagmobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button b1;
        //EditText ed1,ed2;

        b1 = (Button)findViewById(R.id.btnLogin);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed1 = (EditText)findViewById(R.id.editText1);
                EditText ed2 = (EditText)findViewById(R.id.editText2);

                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin"))
                {
                    Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(homeIntent);
                            finish();
                        }
                    }, SPLASH_TIME_OUT);
                }
                else
                    {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });



        /*
        Button btn = (Button) findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MyApp", "This is a log message");
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

            }
        });
        */
    }
}
