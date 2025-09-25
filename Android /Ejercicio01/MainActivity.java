package com.example.ad2025_ejercicio01;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String Tag ="ESTADO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag,  "OnStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag,  "OnStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag,  "OnDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag,  "OnPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag,  "OnResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Tag,  "OnRestart()");
    }
}

