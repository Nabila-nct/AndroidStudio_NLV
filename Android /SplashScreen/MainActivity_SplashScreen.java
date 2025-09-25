package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  Animation tyong1, tyong2;
  ImageView imgTyong1, imgTyong2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        tyong1= AnimationUtils.loadAnimation(this,R.anim.tyong1_anim);
        tyong2= AnimationUtils.loadAnimation(this,R.anim.tyong2_anim);
        imgTyong1=findViewById(R.id.p1);
        imgTyong2=findViewById(R.id.p2);

        imgTyong1.startAnimation(tyong1);
        imgTyong2.startAnimation(tyong2);

      Handler handler= new Handler();
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          startActivity(new Intent(MainActivity.this, activity_principal.class));

          finish();
        }
      },1500);



    }
}
