package com.gomara.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.gomara.R;

public class SplashScreen extends Activity {

    private ImageView img2;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sceen);

        img2 = findViewById(R.id.imgLogoGomaraSplash);
        img2.setImageResource(R.drawable.gomara);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Activity_Login.class);
                startActivity(i);
                finish();
            }
        },2000);

    }

}
