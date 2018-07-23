package edmt.dev.androidgridlayout.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edmt.dev.androidgridlayout.Drawer;
import edmt.dev.androidgridlayout.R;


/*public class SplashScreen extends AppCompatActivity {
    SharedPreferences sharedPreferences=getSharedPreferences("My",MODE_PRIVATE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sharedPreferences.getString("user","").equals(""))
                {

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), Drawer.class);
                    intent.putExtra("user",sharedPreferences.getString("user",""));
                    startActivity(intent);
                }





            }
        },2000);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
*/









import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class SplashScreen extends Activity {

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                Intent i = new Intent(SplashScreen.this, Drawer.class);
                SplashScreen.this.startActivity(i);
                SplashScreen.this.finish();
            }
        };

        protected void onCreate (Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            if (!prefs.getBoolean("first_time", false)) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("first_time", true);
                editor.commit();
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                this.startActivity(i);
                this.finish();
            } else {
                this.setContentView(R.layout.activity_splash_screen);
                handler.sendEmptyMessageDelayed(0, 2000);
            }

        }
    }
