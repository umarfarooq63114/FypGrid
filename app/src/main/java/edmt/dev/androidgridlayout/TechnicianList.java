package edmt.dev.androidgridlayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
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
/*
        name=(TextView) dialog.findViewById(R.id.name);
        call=(ImageView) dialog.findViewById(R.id.call);
        msg=(ImageView) dialog.findViewById(R.id.msg);
        info=(ImageView) dialog.findViewById(R.id.info);
*/
        own = new ArrayList<>();
        String name = null, phone = null;
        float ratingbar = (float) 1.5;

        for (int i = 1; i <= 50; i++) {


            own.add(new Technician(R.drawable.pix, Tname+"" + i,
                    "03104121" + i, "offline", "Technician", (float) ratingbar));
            own.add(new Technician(R.drawable.pic, Tname+" farooq " + i,
                    "69875" + i, "online", "Electrician", (float) ratingbar));

        }


        technicianAdapter = new TechnicianAdapter(this, own);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(technicianAdapter);

    }
    public  void imageClick(View view)
    {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialogue);
        dialog.show();
        //name.setText(Tname);

    }
}
