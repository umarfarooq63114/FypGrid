package edmt.dev.androidgridlayout;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Comparator;
import java.util.List;

import edmt.dev.androidgridlayout.Retrofit.GetRetrofit;
import edmt.dev.androidgridlayout.Retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.Collection;
import java.util.Collections;


public class TechnicianList extends AppCompatActivity {
    ArrayList<Technician> own;
    static int technicianPic;
    private RecyclerView recyclerView;
    private RetrofitClient apiInterface;
    private TechnicianAdapter technicianAdapter;
    Dialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;
    String Tname;

    Double val;

    private static final int REQUEST_CALL = 1;


    TextView name;
    ImageView call, msg, info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_list);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        own = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Tname = "umar";
        int pic1 = R.drawable.pix, pic2 = R.drawable.family_time;

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
                Call<List<Technician>> cal = apiInterface.getLostThings();
                RetrofitClient apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
                cal.enqueue(new Callback<List<Technician>>() {
                    @Override
                    public void onResponse(Call<List<Technician>> call, Response<List<Technician>> response) {
                        List<Technician> list = response.body();
                        if (response.isSuccessful()) {

                            for (Technician x : list) {
                                val = x.getRating();
                                int stat = x.getStatus();
                                if (stat == 1) {
                                    own.add(x);
                                    //technicianPic=((int) own.get(i).getImage());
                                }
                            }

                            Toast.makeText(TechnicianList.this, "connection successfull", Toast.LENGTH_SHORT).show();
                            Log.d("MTAG", "onResponse: is successfully: " + response.body());


                            //Collections.reverse(own);
                            descendingSorting();
                            //Collections.reverse(own);
                            technicianAdapter = new TechnicianAdapter(TechnicianList.this, own);
                            recyclerView.setAdapter(technicianAdapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Technician>> call, Throwable t) {
                        Log.d("MTAG", "No Internet Connection " + t.getLocalizedMessage());
                        Toast.makeText(TechnicianList.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });


                technicianAdapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // listGettingFromDB();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        // getting values from api

        apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        Call<List<Technician>> cal = apiInterface.getLostThings();
        RetrofitClient apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        cal.enqueue(new Callback<List<Technician>>() {
            @Override
            public void onResponse(Call<List<Technician>> call, Response<List<Technician>> response) {
                List<Technician> list = response.body();
                if (response.isSuccessful()) {

                    for (Technician x : list) {
                        val = x.getRating();
                        int stat = x.getStatus();
                        if (stat == 1) {
                            own.add(x);
                            //technicianPic=((int) own.get(i).getImage());
                        }
                    }

                    Toast.makeText(TechnicianList.this, "connection successfull", Toast.LENGTH_SHORT).show();
                    Log.d("MTAG", "onResponse: is successfully: " + response.body());


                    //Collections.reverse(own);

                    //Collections.reverse(own);
                    technicianAdapter = new TechnicianAdapter(TechnicianList.this, own);
                    recyclerView.setAdapter(technicianAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Technician>> call, Throwable t) {
                Log.d("MTAG", "No Internet Connection " + t.getLocalizedMessage());
                Toast.makeText(TechnicianList.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


        ActionBar actionBar = getSupportActionBar();
// actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("3984FF")));
        //actionBar.setTitle("Items List");

    }

  /*  public void listGettingFromDB() {


    }*/


    private void descendingSorting() {
        Collections.sort(own, new Comparator<Technician>() {
            //Collections.sort(own, new Comparator<Count>() {

            @Override
            public int compare(Technician o1, Technician o2) {
                return Double.compare(o2.getRating(), o1.getRating());
            }
        });
    }


    private void asccendingSorting() {
        Collections.sort(own, new Comparator<Technician>() {
            //Collections.sort(own, new Comparator<Count>() {

            @Override
            public int compare(Technician o1, Technician o2) {
                return Double.compare(o1.getRating(), o2.getRating());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    technicianAdapter.filter("");
                    //recyclerView.clearFocus();
                } else {
                    technicianAdapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort) {
            //Toast.makeText(this, "Sort", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_asc_sort) {
            //Toast.makeText(this, "Ascend Sort", Toast.LENGTH_SHORT).show();
            asccendingSorting();
            technicianAdapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.action_desc_sort) {
            //Toast.makeText(this, "Descend Sort", Toast.LENGTH_SHORT).show();
            descendingSorting();
            technicianAdapter.notifyDataSetChanged();

            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


