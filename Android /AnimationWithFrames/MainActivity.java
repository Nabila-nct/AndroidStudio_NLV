package com.example.animationwithframes;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

  private AnimationDrawable animationKid;

  private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imagen=findViewById(R.id.imageAnimation);
        imagen.setBackgroundResource(R.drawable.kid_animation);
        animationKid=(AnimationDrawable) imagen.getBackground();
        animationKid.run();
    }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
      if(event.getAction()==MotionEvent.ACTION_DOWN){
        if(animationKid.isRunning()){
          animationKid.stop();
        }else{
          animationKid.start();
        }
      }
    return super.onTouchEvent(event);
  }
}
