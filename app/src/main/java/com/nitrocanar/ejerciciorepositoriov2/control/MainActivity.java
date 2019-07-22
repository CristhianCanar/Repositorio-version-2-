package com.nitrocanar.ejerciciorepositoriov2.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nitrocanar.ejerciciorepositoriov2.Daniel;
import com.nitrocanar.ejerciciorepositoriov2.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private Button btnDavid,btnDaniel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenciar();

    }

    private void referenciar() {

        btnDavid = findViewById(R.id.btnDavid);
        btnDavid.setOnClickListener(this);
        btnDaniel = findViewById(R.id.btnDaniel);
        btnDaniel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDavid:

                intent = new Intent(MainActivity.this, AudioDavidActivity.class);
                startActivity(intent);

                break;
            case R.id.btnEdwin:
                break;
            case R.id.btnDaniel:

                Intent llever = new Intent(getApplicationContext(), Daniel.class);
                startActivity(llever);

                break;
            case R.id.btnCristhian:
                break;
        }
    }
}
