package com.example.learnforexstrategies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnforexstrategies.data.SaveSharefPreference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button loginButton;
    DatabaseReference reference;
    EditText inputUsername,inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        inputPassword = findViewById(R.id.inputPassword);
        inputUsername = findViewById(R.id.inputUsername);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setEnabled(false);
                loginButton.setText("LOADING...");
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(inputUsername.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(Login.this, "data ada", Toast.LENGTH_SHORT).show();

                            // ambil data password dari firebase
                            String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();
                            String username = dataSnapshot.child("username").getValue().toString();
                            String email = dataSnapshot.child("email").getValue().toString();
                            if (passwordFromFirebase.equals(inputPassword.getText().toString())) {
                                Intent login = new Intent(Login.this, SignUpConfirm.class);
                                SaveSharefPreference.setLoggedIn(getApplicationContext(), true);
                                SaveSharefPreference.setNameIn(getApplicationContext(), username);
                                SaveSharefPreference.setEmail(getApplicationContext(), email);
                                startActivity(login);
                            }



                        } else {
                            Toast.makeText(Login.this, "username tidak ada!!", Toast.LENGTH_SHORT).show();
                            //ubah state menjadi loading
                            loginButton.setEnabled(true);
                            loginButton.setText("SIGN IN");

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
