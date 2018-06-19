package edmt.dev.androidgridlayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static edmt.dev.androidgridlayout.DeviceDetails.dev_defects;
import static edmt.dev.androidgridlayout.DeviceDetails.dev_model;
import static edmt.dev.androidgridlayout.DeviceDetails.dev_name;
import static edmt.dev.androidgridlayout.TechnicianDetail.name;

public class Cart extends AppCompatActivity {
Dialog dialog;

Button cancel,ok;
TextView dModel,dDefect,tName,Time,Date,dName;

    private static Button date, time;
    private static TextView set_date, set_time;
    private static final int Date_id = 0;
    private static final int Time_id = 1;
    String date1,time1;
    Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        date = (Button) findViewById(R.id.selectdate);
        time = (Button) findViewById(R.id.selectTime);
        set_date = (TextView) findViewById(R.id.set_date);
        set_time = (TextView) findViewById(R.id.set_time);
        btnCart=findViewById(R.id.addCart);





        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show Date dialog
                showDialog(Date_id);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show time dialog
                showDialog(Time_id);
            }
        });


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Cart.this);
                dialog.setContentView(R.layout.confirm_dialogue);
                dName =  dialog.findViewById(R.id.dName);
                dModel =  dialog.findViewById(R.id.dModel);
                dDefect =  dialog.findViewById(R.id.dDefect);
                tName =  dialog.findViewById(R.id.tName);
                Time = dialog.findViewById(R.id.Time);
                Date =  dialog.findViewById(R.id.Date);
                cancel =  dialog.findViewById(R.id.cancel);
                ok =  dialog.findViewById(R.id.ok);

                dName.setText(dev_name);
                dModel.setText(dev_model);
                dDefect.setText(dev_defects);
                tName.setText(name);
                Time.setText(time1);
                Date.setText(date1);
                dialog.show();

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Cart.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    protected Dialog onCreateDialog(int id) {

        // Get the calander
        Calendar c = Calendar.getInstance();

        // From calander get the year, month, day, hour, minute
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        switch (id) {
            case Date_id:

                // Open the datepicker dialog
                return new DatePickerDialog(Cart.this, date_listener, year,
                        month, day);
            case Time_id:

                // Open the timepicker dialog
                return new TimePickerDialog(Cart.this, time_listener, hour,
                        minute, false);

        }
        return null;
    }

    // Date picker dialog
    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // store the data in one string and set it to text
             date1 = String.valueOf(month) + "/" + String.valueOf(day)
                    + "/" + String.valueOf(year);
            set_date.setText(date1);
        }
    };
    TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            // store the data in one string and set it to text
             time1 = String.valueOf(hour) + ":" + String.valueOf(minute);
            set_time.setText(time1);
        }
    };


}
