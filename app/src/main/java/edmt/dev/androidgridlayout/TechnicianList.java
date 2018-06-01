package edmt.dev.androidgridlayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TechnicianList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TechnicianAdapter technicianAdapter;

    private List<Technician> own;
    String tName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        own = new ArrayList<>();
        String name = null, phone = null;
        float ratingbar = (float) 1.5;


        for (int i = 1; i <= 50; i++) {


            own.add(new Technician(R.drawable.pix, "umar " + i,
                    "03104121" + i, "offline", "Technician", (float) ratingbar));
            own.add(new Technician(R.drawable.pic, "farooq " + i,
                    "69875" + i, "online", "Electrician", (float) ratingbar));

        }


        technicianAdapter = new TechnicianAdapter(this, own);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(technicianAdapter);

    }
}
