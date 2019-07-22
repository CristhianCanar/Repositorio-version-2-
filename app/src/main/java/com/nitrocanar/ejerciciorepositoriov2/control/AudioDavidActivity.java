package com.nitrocanar.ejerciciorepositoriov2.control;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nitrocanar.ejerciciorepositoriov2.R;

import java.io.IOException;

public class AudioDavidActivity extends AppCompatActivity {

    private Button btnStart, btnStop, btnRecord;
    private TextView tvMensaje;

    private static final String LOG_TAG = "AudioRecord";
    private static final int REQUEST_RECORD_AUIDIO_PERMISSION = 200;
    private static String fileName = null;

    private MediaRecorder recorder = null;
    private MediaPlayer player = null;

    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case REQUEST_RECORD_AUIDIO_PERMISSION:

                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }

        if (!permissionToRecordAccepted) finish();

    }

    private void onRecord(boolean start){

        if (start){
            startRecording();
        }else {
            stopRecording();
        }

    }

    private void onPlay(boolean start){

        if (start){
            startPlaying();
        }else {
            stopPlaying();
        }
    }

    private void startPlaying(){

        player = new MediaPlayer();

        try {

            player.setDataSource(fileName);
            player.prepare();
            player.start();

        }catch (IOException e){
            Log.e(LOG_TAG, "Prepare() File");
        }

    }

    private void stopPlaying(){

        player.release();
        player = null;

    }

    private void startRecording(){

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {

            recorder.prepare();

        }catch (IOException e){
            Log.e(LOG_TAG, "Prepare() filed");
        }

        recorder.start();

    }

    private void stopRecording(){

        recorder.stop();
        recorder.release();
        recorder = null;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_david);

        referenciar();

        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/auidiorecorttest.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUIDIO_PERMISSION);




    }

    private void referenciar() {

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPlay(true);
            }
        });

        btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecording();
            }
        });

        tvMensaje = findViewById(R.id.tvMensaje);

        btnRecord = findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecord(true);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (recorder != null){
            recorder.release();
            recorder = null;
        }

        if (player != null){
            player.release();
            player = null;
        }

    }
}
