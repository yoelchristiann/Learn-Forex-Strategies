package com.example.learnforexstrategies;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.learnforexstrategies.data.SaveSharefPreference;

public class SplashScreenAct extends AppCompatActivity {
    private static Boolean loginStat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        loginStat = SaveSharefPreference.getLoggedStatus(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loginStat) {
                    startActivity(new Intent(SplashScreenAct.this, Drawer.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreenAct.this, MainActivity.class));
                    finish();
                }

            }
        },1500);
    }
}
