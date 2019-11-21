package com.example.learnforexstrategies;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {
    DatabaseReference reference;
    WebView video;
    TextView tv_title,tv_artikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        video = findViewById(R.id.videoview);
        tv_title = findViewById(R.id.tv_title);
        tv_artikel = findViewById(R.id.tv_artikel);

        // setting
        video.setWebViewClient(new WebViewClient());
        video.setWebChromeClient(new WebChromeClient());
        video.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        video.getSettings().setJavaScriptEnabled(true);
        video.getSettings().setPluginState(WebSettings.PluginState.ON);
        video.getSettings().setDefaultFontSize(18);

        String id;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id= null;
            } else {
                id= extras.getString("KEY");
            }
        } else {
            id= (String) savedInstanceState.getSerializable("KEY");
        }
        reference = FirebaseDatabase.getInstance().getReference().child("home").child(id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(DetailActivity.this, dataSnapshot.child("title").getValue().toString(), Toast.LENGTH_SHORT).show();
                    muatVideo(dataSnapshot.child("video").getValue().toString());
                    tv_artikel.setText(dataSnapshot.child("text").getValue().toString());
                    tv_title.setText(dataSnapshot.child("title").getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void muatVideo(String kode_youtube) {

        String kodeHTML = "<head></head><body>" +
                "<iframe width=\"400\" height=\"315\" src=\"https://www.youtube.com/embed/" +
                kode_youtube +
                "\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>" +
                "</body>";
        video.loadData(kodeHTML, "text/html; charset=utf-8", null);
    }
}
