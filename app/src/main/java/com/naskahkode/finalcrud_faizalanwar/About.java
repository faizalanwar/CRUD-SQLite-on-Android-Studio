package com.naskahkode.finalcrud_faizalanwar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import android.widget.Button;


public class About extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
    public void openActivity2() {
        Intent intent = new Intent(this, Daftar.class);
        startActivity(intent);
    }
}

