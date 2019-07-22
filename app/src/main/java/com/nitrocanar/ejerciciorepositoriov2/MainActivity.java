package com.nitrocanar.ejerciciorepositoriov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDavid:
                break;
            case R.id.btnEdwin:
                break;
            case R.id.btnDaniel:

                Intent llever = new Intent(getApplicationContext(),Daniel.class);
                startActivity(llever);

                break;
            case R.id.btnCristhian:
                break;
        }
    }
}
