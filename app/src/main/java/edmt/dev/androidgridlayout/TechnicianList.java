package edmt.dev.androidgridlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
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
        int pic1 =R.drawable.pix,pic2=R.drawable.pic;
/*

*/
        own = new ArrayList<>();
        String name = null, phone = null;
        float ratingbar = (float) 1.5;

        for (int i = 1; i <= 50; i++) {


            own.add(new Technician(pic1, Tname+"" + i,
                    "03104121" + i, "offline", "Technician", (float) ratingbar));
            own.add(new Technician(pic2, Tname+" farooq " + i,
                    "69875" + i, "online", "Electrician", (float) ratingbar));

        }

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


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //own.getFilter().filter(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }



}
