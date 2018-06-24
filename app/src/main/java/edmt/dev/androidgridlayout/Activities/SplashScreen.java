package edmt.dev.androidgridlayout.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edmt.dev.androidgridlayout.R;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        },2000);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
