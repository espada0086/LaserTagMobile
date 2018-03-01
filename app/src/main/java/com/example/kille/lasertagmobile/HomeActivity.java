package com.example.kille.lasertagmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn = (Button) findViewById(R.id.btnFire);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MyApp", "This is a log message");
                Toast.makeText(getApplicationContext(), "Pew Pew", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
