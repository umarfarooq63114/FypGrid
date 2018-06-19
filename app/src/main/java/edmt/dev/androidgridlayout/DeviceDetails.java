package edmt.dev.androidgridlayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class DeviceDetails extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner1, spinner2, spinner3;
    private Button btnSubmit;
    TextView tvModel, tvDefect, tvName;
    int a = 1,opt=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_details);
spinner2=findViewById(R.id.spinner2);
        addItemsOnSpinner1();

        addItemsOnSpinner3();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> deviceModel = new ArrayList<String>();
        if (a == 1) {
            deviceModel.add("SAMSUNG");
            deviceModel.add("APPLE");
            deviceModel.add("HUAWEI");
            deviceModel.add("HTC");
            deviceModel.add("QMOBILE");
            deviceModel.add("OTHER");
        }
        if (a == 2 ) {

            deviceModel.add("DELL");
            deviceModel.add("HP");
            deviceModel.add("LENOVO");
            deviceModel.add("APPLE");
            deviceModel.add("ACER");
            deviceModel.add("OTHER");
        }

        if (a == 3) {
            deviceModel.add("SONY");
            deviceModel.add("LG");
            deviceModel.add("SAMSUNG");
            deviceModel.add("PANASONIC");
            deviceModel.add("OTHER");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, deviceModel);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

    }




    public void addItemsOnSpinner3() {

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        List<String> deviceIssue = new ArrayList<String>();

        deviceIssue.add("Screen Broken");
        deviceIssue.add("Mic Issue");
        deviceIssue.add("On/Off issue");
        deviceIssue.add("Battery Issue");
        deviceIssue.add("Other Internal Issue");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, deviceIssue);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter);
    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);




    }

    /*public void addListenerOnSpinner2ItemSelection() {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }*/
    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);


        tvModel = findViewById(R.id.tvModel);
        tvName = findViewById(R.id.tvName);
        tvDefect = findViewById(R.id.tvDefect);


        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DeviceDetails.this,
                        "Model : " + String.valueOf(spinner1.getSelectedItem()) +
                                "\nName : " + String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            List<String> deviceName = new ArrayList<String>();
            if ( i==0) {
                deviceName.add("GALAXY S Series");
                deviceName.add("GALAXY J Series");
                deviceName.add("GALAXY A Series");
                deviceName.add("GALAXY C Series");
                deviceName.add("GALAXY Note Series");
            }
            else if ( i==1) {
                deviceName.add("iPHONE iPOD ");
                deviceName.add("iPHONE iPAD");
                deviceName.add("iPHONE S Series");
            }

            else if (i==2) {
                deviceName.add("HAWEII P SERIES");
                deviceName.add("HAWEII MATE Series");
                deviceName.add("HAWEII HONOR Series");
                deviceName.add("HAWEII Y Series");
                deviceName.add("HAWEII G Series");
            }


            else if (i==3) {
                deviceName.add("HTC DESIRE SERIES");
                deviceName.add("HTC ONE/ONE S");
                deviceName.add("HTC BUTTERFLY");
                deviceName.add("HTC U11/U11+");
                deviceName.add("HTC 10");
            }

            else if (i==4) {
                deviceName.add("INSPIRON 3000 SERIES");
                deviceName.add("INSPIRON 5000 SERIES");
                deviceName.add("INSPIRON 7000 SERIES");
                deviceName.add("G SERIES");

            }


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, deviceName);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(dataAdapter);
        }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}