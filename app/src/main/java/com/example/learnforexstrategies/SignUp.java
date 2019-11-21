package com.example.learnforexstrategies;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    DatabaseReference reference;
    TextView inputEmail, inputUsername, inputPassword;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputEmail = findViewById(R.id.inputEmail);
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        signupButton = findViewById(R.id.signupButton);


        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                signupButton.setEnabled(false);
                signupButton.setText("LOADING...");

                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(inputUsername.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("username").setValue(inputUsername.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(inputPassword.getText().toString());
                        dataSnapshot.getRef().child("email").setValue(inputEmail.getText().toString());
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
