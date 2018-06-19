package edmt.dev.androidgridlayout;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {


    public static String data;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        data=parent.getItemAtPosition(pos).toString();


        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + data,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

