package com.example.kille.lasertagmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //Import data from LoginActivity Page
        FirebaseUser currentUser = mAuth.getCurrentUser();
        displayName = currentUser.getDisplayName();
        //String value = getIntent().getStringExtra("USER_NAME");
        TextView username = (TextView) findViewById(R.id.textUserName);
        username.setText(displayName);

        //Fire Button
        Button btn = (Button) findViewById(R.id.btnFire);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MyApp", "This is a log message");
                Toast.makeText(getApplicationContext(), "Pew Pew", Toast.LENGTH_SHORT).show();

            }
        });

        //Configure Bluetooth Button
        Button btnBlue = (Button)findViewById(R.id.btn_Bluetooth);
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Bluetooth.class));
            }
        });

        //Settings Button
        Button btnSet = (Button)findViewById(R.id.btnSettings);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });




    }
}
