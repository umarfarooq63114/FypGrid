package edmt.dev.androidgridlayout;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.squareup.picasso.Picasso;

import edmt.dev.androidgridlayout.Activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import edmt.dev.androidgridlayout.Model.Category;
import edmt.dev.androidgridlayout.Model.Customer;
import edmt.dev.androidgridlayout.Retrofit.GetRetrofit;
import edmt.dev.androidgridlayout.Retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static edmt.dev.androidgridlayout.Fragments.Login_Fragment.UsergetEmailId;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static FragmentManager fragmentManager;
    ArrayList<Category> own;

    String userEmaiId;
    ArrayList<String> own1;
    private RecyclerView recyclerView;
    private RetrofitClient apiInterface;
    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;
    private MainAdapter mainAdapter;
    List<Address> address;
    public int z;
    Geocoder geocoder;
    String token = "";
    RelativeLayout rootLayout;
    public String imagage;
    static String latitude, longitude;
    SwipeRefreshLayout swipeRefreshLayout;
    LocationManager locationManager;
    ImageView imageView;
    Toolbar signout;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        imageFun();
        fragmentManager = getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.user_image);


        SharedPreferences sharedPreferences = getSharedPreferences("My", MODE_PRIVATE);
        sharedPreferences.edit().putString("user", getIntent().getStringExtra("user")).apply();


        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        own = new ArrayList<>();
        own1 = new ArrayList<String>();
        recyclerView.setLayoutManager(new LinearLayoutManager(Drawer.this));
        recyclerView.setHasFixedSize(true);

        // getting values from api

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mainAdapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        Call<List<Category>> cal = apiInterface.getCategoriesList();
        RetrofitClient apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        cal.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> list = response.body();
                if (response.isSuccessful()) {
                    for (Category x : list) {
                        own.add(x);
                    }
                    Toast.makeText(Drawer.this, "connection successfull", Toast.LENGTH_SHORT).show();
                    Log.d("MTAG", "onResponse: is successfully: " + response.body());

                    mainAdapter = new MainAdapter(Drawer.this, own);
                    recyclerView.setAdapter(mainAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("MTAG", "No Internet Connection " + t.getLocalizedMessage());
                Toast.makeText(Drawer.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            userEmaiId = intent.getStringExtra("email");
        }


        //  mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        //setSingleEvent(mainGrid);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);










        PusherOptions options = new PusherOptions();
        options.setCluster("ap2");
        Pusher pusher = new Pusher("d994b17cd676131c6cb2", options);

        final Channel channel = pusher.subscribe("sFinder");
        Log.d("MTAG", "test");
        channel.bind("info", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                Log.d("MTAG",data);
                Gson gson = new Gson();

                Notify message = gson.fromJson(data,Notify.class);
                // Serializing Json to Respective POJO


                //notif.add(new Notificaton(noti));
                Intent i = new Intent(Drawer.this, Feedback.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(Drawer.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notification = new NotificationCompat.Builder(Drawer.this, "Channel1")
                        .setContentTitle(message.getData().getT_name())
                        .setContentText("Work Done!")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                        .setSmallIcon(R.drawable.error)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(101, notification.build());

                //EventBus.getDefault().post(new MyClass message);
                //EventBus.getDefault().post(message);





//Notificaton notificaton=new Notificaton();
                //Toast.makeText(MainActivity.this, "abc: "+noti.getTechnician_id().toString(), Toast.LENGTH_SHORT).show();
                //Eventbus code for posting data to set in views






            }
        });

        pusher.connect();






















    }


    public void imageFun() {
        apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        Call<List<Customer>> call = apiInterface.getCustomerList();
        RetrofitClient apiInterfac = GetRetrofit.getInstance().create(RetrofitClient.class);

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                List<Customer> list = response.body();
                if (response.isSuccessful()) {


                    for (int i = 0; i < list.size(); i++) {

                        if (list.get(i).getEmail().equals(UsergetEmailId)) {
                            imagage = list.get(i).getImage();

                        }
                    }


                    Toast.makeText(Drawer.this, "Image lagny lagi ....connection successfull", Toast.LENGTH_SHORT).show();
                    Log.d("MTAG", "onResponse: is successfully: " + response.body());

                }

            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.d("MTAG", "No Internet Connection " + t.getLocalizedMessage());
                Toast.makeText(Drawer.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Drawer.this);
            builder.setMessage("Are you sure to quit?");
            builder.setCancelable(true);
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(Drawer.this, Drawer.class));
        } else if (id == R.id.user_image) {
            Picasso.with(Drawer.this).load(imagage)
                    .resize(100, 100)
                    .into(imageView);
        } else if (id == R.id.nav_technician) {
            startActivity(new Intent(Drawer.this, TechnicianList.class));
        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            // If savedinstnacestate is null then replace login fragment
            SharedPreferences sharedPreferences = getSharedPreferences("My", MODE_PRIVATE);
            //sharedPreferences.edit().putString("user",getIntent().getStringExtra("user")).clear();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            finish();
            startActivity(new Intent(Drawer.this, MainActivity.class));
               /* fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new Login_Fragment(),
                                Utils.Login_Fragment).commit();*/
            //Toast.makeText(this, "Hi baby", Toast.LENGTH_SHORT).show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

