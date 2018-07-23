package edmt.dev.androidgridlayout.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edmt.dev.androidgridlayout.Custom.CustomToast;
import edmt.dev.androidgridlayout.Drawer;
import edmt.dev.androidgridlayout.Model.Customer;
import edmt.dev.androidgridlayout.Model.Login;
import edmt.dev.androidgridlayout.R;
import edmt.dev.androidgridlayout.Retrofit.GetRetrofit;
import edmt.dev.androidgridlayout.Retrofit.RetrofitClient;
import edmt.dev.androidgridlayout.Technician;
import edmt.dev.androidgridlayout.TechnicianAdapter;
import edmt.dev.androidgridlayout.TechnicianList;
import edmt.dev.androidgridlayout.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Login_Fragment extends Fragment implements OnClickListener {
    private  View view;
    ArrayList<Technician> own;
    private RetrofitClient apiInterface;
    String token = "";
    private EditText emailid, password;
    private Button loginButton;

    public static String UsergetEmailId;


    private TextView forgotPassword, signUp;
    private CheckBox show_hide_password;
    private LinearLayout loginLayout;
    private Animation shakeAnimation;
    private FragmentManager fragmentManager;
    TechnicianList list;

     public Login_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        own = new ArrayList<>();
        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = view.findViewById(R.id.login_emailid);
        password = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.loginBtn);
        forgotPassword = view.findViewById(R.id.forgot_password);
        signUp = view.findViewById(R.id.createAccount);
        show_hide_password = view
                .findViewById(R.id.show_hide_password);
        loginLayout = view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);


    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);
        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;

            case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUp_Fragment(),
                                Utils.SignUp_Fragment).commit();
                break;
        }

    }

    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        UsergetEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();
        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(UsergetEmailId);

        // Check for both field is empty or not
        if (UsergetEmailId.equals("") || UsergetEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");

        }
        // Check if email id is valid or not
        else if (!m.find()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
        }

        else {
            final SharedPreferences sharedPreferences=getActivity().getSharedPreferences("My",MODE_PRIVATE);
        RetrofitClient apiInterface = GetRetrofit.getInstance().create(RetrofitClient.class);
        Login login = new Login(UsergetEmailId, getPassword);
        Call<Customer> call = apiInterface.login(login);

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse( Call<Customer> call,  Response<Customer> response) {
                if (response.isSuccessful()) {
                    token = response.body().getToken();
                    Toast.makeText(getContext(), token, Toast.LENGTH_SHORT).show();

                    if(sharedPreferences.getString("user","").equals(""))
                    {
                        Intent intent = new Intent(getContext(), Drawer.class);
                        intent.putExtra("token", token);
                        intent.putExtra("email",UsergetEmailId);
                        intent.putExtra("user",sharedPreferences.getString("user",""));
                        startActivity(intent);
                    }
                    else
                    {
                        /*Intent intent = new Intent(getContext(), Drawer.class);
                        intent.putExtra("token", token);
                        intent.putExtra("user",sharedPreferences.getString("user",""));
                        startActivity(intent);*/
                    }




                } else {
                    Toast.makeText(getContext(), "Username or Password is incorrect!" +response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(getContext(), "Issue in method"+t.getLocalizedMessage()+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}





/*
            // getting values from api
            final EditText user_name=view.findViewById(R.id.login_emailid);
            final EditText login_password=view.findViewById(R.id.login_password);
            apiInterface = GetRetrofit.getRetrofit().create(RetrofitClient.class);
            Call<List<Customer>> cal = apiInterface.getCustomerList();
            RetrofitClient apiInterface = GetRetrofit.getRetrofit().create(RetrofitClient.class);
            cal.enqueue(new Callback<List<Customer>>() {
                @Override
                public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                    List<Customer> list = response.body();
                    if (response.isSuccessful()) {

                        for (int i = 1; i < list.size(); i++) {
                            if (user_name.getText().toString().equals(list.get(i).getEmail())
                                    && login_password.getText().toString().equals(list.get(i).getPassword())) {
                                startActivity((new Intent(getActivity(), Drawer.class)));
                                break;
                            }

else {
                                //Toast.makeText(getActivity(), "connection successfull", Toast.LENGTH_SHORT).show();
                                //new CustomToast().Show_Toast(getActivity(), view,
                                  //      "Invalid Username or Password");
                                Log.d("MTAG", "onResponse: is successfully: " + response.body());
                            }

                        }

                    }
                }
                @Override
                public void onFailure(Call<List<Customer>> call, Throwable t) {
                    Log.d("MTAG", "No Internet Connection " + t.getLocalizedMessage());
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });
*/

                //Intent intent = new Intent(getContext(), Drawer.class);
            //intent.putExtra("token", token);
            //startActivity(intent);

                    // else{
                //Toast.makeText(getContext(), "Username or Password is incorrect!" +response.message(), Toast.LENGTH_SHORT).show();
            //}


        //}
                    }





