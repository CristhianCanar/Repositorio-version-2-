package com.nitrocanar.ejerciciorepositoriov2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Daniel extends AppCompatActivity {

    Button btn_g;
    TextView pregunta;
    private static final int permiso = 100;
    TextView solucion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daniel);

        referenciar();
    }

    private void referenciar() {

        btn_g = findViewById(R.id.btn_grabar);
        pregunta = findViewById(R.id.pregunta);
        solucion = findViewById(R.id.txt_grabar);

        btn_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comenzar();
            }
        });

    }



    private void Comenzar() {

        Intent ver = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        ver.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        ver.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        ver.putExtra(RecognizerIntent.EXTRA_PROMPT,"Respuesta");
        try {
            startActivityForResult(ver,permiso);
        }catch (ActivityNotFoundException e){
            Toast.makeText(this, "no se puede", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case permiso:{
                if (requestCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    solucion.setText(result.get(0));
                }
                break;
            }
        }
    }
}
