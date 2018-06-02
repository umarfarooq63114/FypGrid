package edmt.dev.androidgridlayout;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TechnicianList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TechnicianAdapter technicianAdapter;
    Dialog dialog;
    String Tname;








    private List<Technician> own;


    TextView name;
    ImageView call,msg,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Tname = "umar";
        int pic1 =R.drawable.pix,pic2=R.drawable.family_time;

 ActionBar actionBar = getSupportActionBar();
       //actionBar.setTitle("Items List");


        own = new ArrayList<>();
        own.addAll(own);
        //notifyDataSetChanged();
        String name = null, phone = null;
        float ratingbar = (float) 1.5;
        own.add(new Technician(pic1, "ali" ,
                "03104121", "offline", "Technician", (float) ratingbar));
        for (int i = 1; i <= 50; i++) {


            own.add(new Technician(pic1, Tname+"" + i,
                    "03104121" + i, "offline", "Technician", (float) ratingbar));
            own.add(new Technician(pic2, Tname+" farooq " + i,
                    "69875" + i, "online", "Electrician", (float) ratingbar));

        }
        own.add(new Technician(pic1, "atif" ,
                "03104121", "offline", "Technician", (float) ratingbar));
        own.add(new Technician(pic1, "adeel" ,
                "03104121", "offline", "Technician", (float) ratingbar));


//AlertDialog.Builder builder=new AlertDialog.Builder(TechnicianList.this);

        technicianAdapter = new TechnicianAdapter(this, own);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(technicianAdapter);

    }
    public  void imageClick(View view)
    {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialogue);
        name=(TextView) dialog.findViewById(R.id.name);
        call=(ImageView) dialog.findViewById(R.id.call);
        msg=(ImageView) dialog.findViewById(R.id.msg);
        info=(ImageView) dialog.findViewById(R.id.info);
        dialog.show();
        name.setText(Tname);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TechnicianList.this,TechnicianDetail.class);
                startActivity(intent);
                //startActivity(new Intent());
                Toast.makeText(TechnicianList.this, "Hamm", Toast.LENGTH_SHORT).show();
            }
        });

        //Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar1);
        //setSupportActionToolBar(toolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    technicianAdapter.filter("");
                    //recyclerView.clearFocus();
                }
                else {
                    technicianAdapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.action_settings){
            //do your functionality here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
