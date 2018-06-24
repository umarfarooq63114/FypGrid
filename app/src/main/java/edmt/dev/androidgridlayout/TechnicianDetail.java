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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import static edmt.dev.androidgridlayout.TechnicianAdapter.ratingValue;

public class TechnicianDetail extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbar;
    static String name = null,exp,add;
    private String tName;
    Button btnAdd;
    ImageView image;
    TextView tvname,tvphone,tvstatus,tCategory,tExp,address;
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
        tExp=findViewById(R.id.tExp);
        address=findViewById(R.id.address);

        btnAdd=findViewById(R.id.btnAdd);


          String phone=null,status=null,category=null;
        int imag = 0;
        float rating = 0;

        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            name = intent.getStringExtra("name");
            phone = intent.getStringExtra("phone");
            exp = intent.getStringExtra("exp");
            status=intent.getStringExtra("status");
            add=intent.getStringExtra("add");
            //imag=intent.getExtras().getInt("image");
            rating=intent.getFloatExtra("rat",4);
            //imag=intent.getIntExtra("image");
            //ratingbar = intent.getFloatExtra("rating", Float.parseFloat(""));

            Toast.makeText(this, "Status "+status, Toast.LENGTH_SHORT).show();
        }


       /* if(status=="1")
        {
            tvstatus.setText("ONLINE");
        }
        else
        if(status=="0")
        {
            tvstatus.setText("OFFLINE");
        }*/
        tvphone.setText(phone.toString());
        tExp.setText(exp.toString());
        tvstatus.setText(status.toString());
        address.setText(add.toString());
       // tCategory.setText(category.toString());
      //  image.setImageResource(imag);

        TratingBar.setRating((float)ratingValue);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TechnicianDetail.this,Cart.class));
            }
        });
       /* image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(TechnicianDetail.this, "Hi Baby", Toast.LENGTH_SHORT).show();
            }
        });*/
        //TratingBar.setRating(ratingbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

        //setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        collapsingToolbar.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);



        // /collapsingToolbar.setCollapsedTitleTextColor(Integer.parseInt("fff"));
        collapsingToolbar.setTitle(""+name);

    }
}