package edmt.dev.androidgridlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DeviceDetails extends AppCompatActivity {
Button btnBack,btnDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_details);
btnDone=findViewById(R.id.btnDone);
btnDone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

       // Toast.makeText(DeviceDetails.this, "welcome", Toast.LENGTH_SHORT).show();
       Intent intent=new Intent(DeviceDetails.this,SelectTechnician.class);




        startActivity(intent);
    }
});



        }
    }

