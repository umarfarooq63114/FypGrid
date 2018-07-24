package edmt.dev.androidgridlayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edmt.dev.androidgridlayout.Model.Brands;
import edmt.dev.androidgridlayout.Model.Faults;
import edmt.dev.androidgridlayout.Model.Items;
import edmt.dev.androidgridlayout.Retrofit.GetRetrofit;
import edmt.dev.androidgridlayout.Retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceDetails extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner1, spinner2, spinner3;
    private Button btnSubmit;
    int a,dummy;
    private RetrofitClient apiInterface, apiInterface1;
    ;
    ArrayList<Brands> own;
    TextView tvModel, tvDefect, tvName;

    String a1;

    static String dev_name, dev_model, dev_defects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_details);

        tvModel = findViewById(R.id.tvModel);
        tvName = findViewById(R.id.tvName);
        tvDefect = findViewById(R.id.tvDefect);


        spinner2 = findViewById(R.id.spinner2);
        Intent intent = getIntent();
        //  if (null != intent) { //Null Checking
        a1 = intent.getExtras().getString("name");
        a = intent.getExtras().getInt("pos");

dummy=a;
        //Toast.makeText(this, "vallues a "+a+" a1 "+a1, Toast.LENGTH_SHORT).show();
        //a = Integer.parseInt(a1);

        addItemsOnSpinner1();
        addItemsOnSpinner3();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        final List<String> deviceModel = new ArrayList<String>();

        //final List<String> deviceModel = new ArrayList<String>();
        apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        Call<List<Items>> cal = apiInterface.getItemList();
        RetrofitClient apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        cal.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                List<Items> list = response.body();
                if (response.isSuccessful()) {
                    int z = a;
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(i).getItem_name();
                        int id = list.get(i).getCategory_id();
                        if (id == (z + 1)) {
                            deviceModel.add("" + name);
                        }
                    }
                    //Toast.makeText(DeviceDetails.this, "connection successfull"+list.get(0).getItem_name(), Toast.LENGTH_SHORT).show();
                    Log.d("MTAG", "onResponse: is successfully: " + response.body());
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DeviceDetails.this,
                            android.R.layout.simple_spinner_item, deviceModel);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(dataAdapter);

                }


            }


            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Log.d("MTAG", "No Internet Connection " + t.getLocalizedMessage());
                Toast.makeText(DeviceDetails.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


        if (a == 0) {
            tvModel.setText("Select Mobile Model");

            /*deviceModel.add("SAMSUNG");
            deviceModel.add("APPLE");
            deviceModel.add("HUAWEI");
            deviceModel.add("HTC");
            deviceModel.add("QMOBILE");*/

        }
        if (a == 1) {
            tvModel.setText("Select Laptop Model");
            /*deviceModel.add("DELL");
            deviceModel.add("HP");
            deviceModel.add("LENOVO");
            deviceModel.add("APPLE");
            deviceModel.add("ACER");*/

        } else if (a == 2) {
            tvModel.setText("Select Television Model");
           /* deviceModel.add("SONY");
            deviceModel.add("LG");
            deviceModel.add("SAMSUNG");
            deviceModel.add("PANASONIC");*/

        }
       /* ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, deviceModel);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);*/

    }


    public void addItemsOnSpinner3() {

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        final List<String> deviceIssue = new ArrayList<String>();

        if (a == 0) {
            tvDefect.setText("Select Mobile Defects");
        } else if (a == 1) {
            tvDefect.setText("Select Laptop Defects");
        }
        if (a == 2) {
            tvDefect.setText("Select Television Defects");
        }

        //own = new ArrayList<Brands>();

        apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        Call<List<Faults>> cal = apiInterface.getFaultList();
        RetrofitClient apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        cal.enqueue(new Callback<List<Faults>>() {
            @Override
            public void onResponse(Call<List<Faults>> call, Response<List<Faults>> response) {
                List<Faults> list = response.body();
                if (response.isSuccessful()) {
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(i).getFault_name();
                        deviceIssue.add("" + name);

                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DeviceDetails.this,
                            android.R.layout.simple_spinner_item, deviceIssue);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner3.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Faults>> call, Throwable t) {
                Log.d("MTAG", "No Internet Connection " + t.getLocalizedMessage());
                Toast.makeText(DeviceDetails.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });






       /* if (a == 0 || a == 1) {
            deviceIssue.add("Screen Broken");
            deviceIssue.add("Mic Issue");
            deviceIssue.add("On/Off issue");
            deviceIssue.add("Speaker issue");
            deviceIssue.add("Battery Issue");
            deviceIssue.add("Other Internal Issue");
        }


        else if (a == 2 ) {
            deviceIssue.add("Screen Broken");
            deviceIssue.add("Color issue");
            deviceIssue.add("On/Off issue");
            deviceIssue.add("Speaker issue");
            deviceIssue.add("Channels");
            deviceIssue.add("Other Internal Issue");
        }*/


    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);


    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                /*Toast.makeText(DeviceDetails.this,
                        "Model : " + String.valueOf(spinner1.getSelectedItem()) +
                                "\nName : " + String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();*/

                dev_model = String.valueOf(spinner1.getSelectedItem());
                dev_name = String.valueOf(spinner2.getSelectedItem());
                dev_defects = String.valueOf(spinner3.getSelectedItem());
                Intent intent = new Intent(DeviceDetails.this, SelectTechnician.class);


                startActivity(intent);

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, final int pos, long l) {
        final List<String> deviceName = new ArrayList<String>();

        // for mobile drop down

        if (a == 0) {
            tvName.setText("Select Mobile Name");
        } else if (a == 1) {
            tvName.setText("Select Laptop Name");
        }
        if (a == 2) {
            tvName.setText("Select Television Name");
        }



        apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        Call<List<Brands>> cal = apiInterface.getBrandsList();
        RetrofitClient apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        cal.enqueue(new Callback<List<Brands>>() {
            @Override
            public void onResponse(Call<List<Brands>> call, Response<List<Brands>> response) {
                List<Brands> list = response.body();
                if (response.isSuccessful()) {
                     int temp=a;
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(i).getBrand_name();

                        if(a==0) {
                            int item_id =list.get(i).getItem_id();
                                if((pos+1) ==  item_id)
                            {
                                deviceName.add("" + name);
                            }
                        }


                        if(a==1) {

                            int item_id = list.get(i).getItem_id();
                            if (item_id >= 6 && item_id <= 10) {
                                int temp1 = item_id;

                                if ((pos + 6) == temp1) {
                                    deviceName.add("" + name);
                                }


                            }
                        }
                        if(a==2) {

                            int item_id = list.get(i).getItem_id();
                            if (item_id >= 11 && item_id <= 15) {
                                int temp1 = item_id;

                                if ((pos + 11) == temp1) {
                                    deviceName.add("" + name);
                                }


                            }
                        }



                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DeviceDetails.this,
                            android.R.layout.simple_spinner_item, deviceName);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Brands>> call, Throwable t) {
                Log.d("MTAG", "No Internet Connection " + t.getLocalizedMessage());
                Toast.makeText(DeviceDetails.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });















        /*if (a == 0) {
            // tvName.setText("Select Mobile Name");
            if (i == 0) {
                deviceName.add("GALAXY S Series");
                deviceName.add("GALAXY J Series");
                deviceName.add("GALAXY A Series");
                deviceName.add("GALAXY C Series");
                deviceName.add("GALAXY Note Series");
            } else if (i == 1) {
                deviceName.add("iPHONE iPOD ");
                deviceName.add("iPHONE iPAD");
                deviceName.add("iPHONE S Series");
            } else if (i == 2) {
                deviceName.add("HAWEII P SERIES");
                deviceName.add("HAWEII MATE Series");
                deviceName.add("HAWEII HONOR Series");
                deviceName.add("HAWEII Y Series");
                deviceName.add("HAWEII G Series");
            } else if (i == 3) {
                deviceName.add("HTC DESIRE SERIES");
                deviceName.add("HTC ONE/ONE S");
                deviceName.add("HTC BUTTERFLY");
                deviceName.add("HTC U11/U11+");
                deviceName.add("HTC 10");
            } else if (i == 4) {
                deviceName.add("QMOBILE NOIR A SERIES");
                deviceName.add("QMOBILE NOIR E SERIES");
                deviceName.add("QMOBILE S SERIES");
                deviceName.add("QMOBILE NOIR X SERIES");
            }
        }


        // for laptop drop down

        if (a == 1) {
            //tvName.setText("Select Laptop Name");
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                deviceName.add("i3");
                deviceName.add("i5");
                deviceName.add("i7");
                deviceName.add("PC");
            }
        }

        // for TV drop down

        if (a == 2) {
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                //tvName.setText("Select Television Name");
                deviceName.add("Flat Panel");
                deviceName.add("LCD");
                deviceName.add("LED");
                deviceName.add("PC Moniter");
            }
        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, deviceName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

*/
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
