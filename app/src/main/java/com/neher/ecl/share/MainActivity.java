package com.neher.ecl.share;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton, registrationButton;
    private EditText mobileView, passwordView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        loginButton.setOnClickListener(this);
        registrationButton.setOnClickListener(this);

    }

    private void init(){
        mobileView = findViewById(R.id.edit_text_mobile);
        passwordView = findViewById(R.id.edit_text_password);
        loginButton = findViewById(R.id.button_login);
        registrationButton = findViewById(R.id.button_registration);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_registration){
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
        }else if(v.getId() == R.id.button_login){
            attemptToLogin();
            Toast.makeText(this, "Login Button is clicked.", Toast.LENGTH_LONG).show();
        }
    }

    private void attemptToLogin(){
        final String mobile = mobileView.getText().toString();
        final String password = passwordView.getText().toString();

        StringRequest logInRequest = new StringRequest(Request.Method.POST, Env.remote.login_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                        SharedPreferences sharedPref = MainActivity.this.getSharedPreferences(Env.sp.sp_name, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        /*editor.putString(Env.sp.user_name, name);
                        editor.putString(Env.sp.user_gender, gender);
                        editor.putInt(Env.sp.user_age, age);*/

                        editor.putString(Env.sp.user_mobile, mobile);
                        editor.putString(Env.sp.user_password, password);
                        editor.putString(Env.sp.access_token, "yes");
                        editor.commit();
                        startActivity(new Intent(MainActivity.this, ShareActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d(TAG, String.valueOf(error));
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("username", mobile);
                params.put("password", password);

                return params;
            }
        };

        MyRequestQueue.getInstance(MainActivity.this).addToRequestque(logInRequest);


    }

}
