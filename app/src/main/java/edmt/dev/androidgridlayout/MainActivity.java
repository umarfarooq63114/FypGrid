package edmt.dev.androidgridlayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;
    ArrayList<Technician> own;
    Button btnSave;
    RecyclerView.LayoutManager layoutManager;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        own = new ArrayList<>();


        //own.add(new User(R.drawable.umar,"umar","farooq"));
        own.add(new Technician(R.drawable.facebook_avatar,"umar","00000", (float) 3.14));
        own.add(new Technician(R.drawable.facebook_avatar,"umar","00000",(float) 3.0));
        own.add(new Technician(R.drawable.facebook_avatar,"umar","00000",(float)3.3));
        own.add(new Technician(R.drawable.facebook_avatar,"umar","00000",(float)3.2));
        own.add(new Technician(R.drawable.facebook_avatar,"umar","00000",(float)4.5));


        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new TechnicianAdapter(this,own);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);







        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if( finalI == 0){
                    Intent intent = new Intent(MainActivity.this,DeviceDetails.class);
                    intent.putExtra("info","This is activity from card item index  "+finalI);
                    startActivity(intent);}

                }
            });
        }
    }
}
