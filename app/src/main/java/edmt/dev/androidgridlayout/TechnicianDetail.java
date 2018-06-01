package edmt.dev.androidgridlayout;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
public class TechnicianDetail extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbar;
    private String tName;
    ImageView image;
    TextView tvname,tvphone,tvstatus,tCategory;
    RatingBar TratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_detail);

        tvphone=findViewById(R.id.phone);
        TratingBar=findViewById(R.id.ratingBar);
        tvstatus=findViewById(R.id.status);
        tCategory=findViewById(R.id.tCategory);
        image=findViewById(R.id.img);

        String name = null,phone=null,status=null,category=null;
        int imag = 0;
        float ratingbar = 0;

        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            category=intent.getStringExtra("category");
            status=intent.getStringExtra("status");
            imag=intent.getExtras().getInt("image");
            ratingbar=intent.getExtras().getFloat("rating");
            //imag=intent.getIntExtra("image");
            //ratingbar = intent.getFloatExtra("rating", Float.parseFloat(""));

        }
        tvphone.setText(phone.toString());
        tvstatus.setText(status.toString());
        tCategory.setText(category.toString());
        image.setImageResource(imag);
        TratingBar.setRating(ratingbar);
        //TratingBar.setRating(ratingbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(""+name);

    }
}