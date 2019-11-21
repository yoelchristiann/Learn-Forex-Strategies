package com.example.learnforexstrategies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpConfirm extends AppCompatActivity {

    Button  LetsGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_confirm);

        LetsGo = findViewById(R.id.LetsGo);

        LetsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent letsgo = new Intent(SignUpConfirm.this, Drawer.class);
                startActivity(letsgo);
                finishAffinity();

            }
        });
    }
}
