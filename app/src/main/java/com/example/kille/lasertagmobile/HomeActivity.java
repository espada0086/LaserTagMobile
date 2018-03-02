package com.example.kille.lasertagmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String value = getIntent().getStringExtra("USER_NAME");
        TextView username = (TextView) findViewById(R.id.textUserName);
        username.setText(value);


        Button btn = (Button) findViewById(R.id.btnFire);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MyApp", "This is a log message");
                Toast.makeText(getApplicationContext(), "Pew Pew", Toast.LENGTH_SHORT).show();

            }
        });

        Button btnBlue = (Button)findViewById(R.id.btn_Bluetooth);

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Bluetooth.class));
            }
        });


    }
}
