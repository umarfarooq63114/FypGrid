package edmt.dev.androidgridlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class DeviceDetails extends AppCompatActivity {
    Button btnBack,btnDone;
    CardView sam;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_details);
        gridLayout=findViewById(R.id.mainGrid);
        sam=(CardView) findViewById(R.id.samsung_cv);
         sam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(DeviceDetails.this, "welcome", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DeviceDetails.this,SelectTechnician.class);




                startActivity(intent);
            }
        });



    }
}

