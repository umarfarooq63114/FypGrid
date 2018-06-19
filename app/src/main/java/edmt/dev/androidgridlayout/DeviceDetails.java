package edmt.dev.androidgridlayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class DeviceDetails extends Activity {

    private Spinner spinner1, spinner2, spinner3;
    private Button btnSubmit;
    TextView tvModel, tvDefect, tvName;
    int a = 1,opt=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_details);

        addItemsOnSpinner1();
        addItemsOnSpinner2();
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


    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        String value = String.valueOf(spinner1.getSelectedItem());
        List<String> deviceName = new ArrayList<String>();
        if ( value == "SAMSUNG") {
            deviceName.add("GALAXY S Series");
            deviceName.add("GALAXY J Series");
            deviceName.add("GALAXY A Series");
            deviceName.add("GALAXY C Series");
            deviceName.add("GALAXY Note Series");
        }
        else if ( value == "APPLE") {
            deviceName.add("iPOD ");
            deviceName.add("iPAD");
            deviceName.add("S Series");
        }

        else if (a == 1 && value == "HUAWEI") {
            deviceName.add("GALAXY S Series");
            deviceName.add("GALAXY J Series");
            deviceName.add("GALAXY A Series");
            deviceName.add("GALAXY C Series");
            deviceName.add("GALAXY Note Series");
        }


        else if (a == 1 && value == "HTC") {
            deviceName.add("GALAXY S Series");
            deviceName.add("GALAXY J Series");
            deviceName.add("GALAXY A Series");
            deviceName.add("GALAXY C Series");
            deviceName.add("GALAXY Note Series");
        }

        if (a == 2 || a==3) {
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
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());


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
}