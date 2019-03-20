package com.example.buddhaair;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buddhaair.Storage.SharedPreferenceManager;
import com.example.buddhaair.Utilities.RetrofitClient;

import java.io.IOException;
import java.net.ServerSocket;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    EditText editTextemail,editTextpassword,editTextusername,editTextname,editTextcpassword;
    Button buttonsignup;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ServerSocket socket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        editTextname =  findViewById(R.id.edittext_fullname);
        editTextcpassword =  findViewById(R.id.edittext_cpassword);
        editTextpassword =  findViewById(R.id.edittext_password);
        editTextusername =  findViewById(R.id.edittext_username);
        editTextemail =  findViewById(R.id.edittext_email);
        buttonsignup =  findViewById(R.id.btn_signup);


        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignup();


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPreferenceManager.getmInstance(this).isLoggedIn()){

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    public  void userSignup(){

        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String username = editTextusername.getText().toString().trim();
        String name = editTextname.getText().toString().trim();
        String confirmpassword = editTextcpassword.getText().toString().trim();


        if (name.isEmpty()){
            editTextname.setError("Please enter full name");
            editTextname.requestFocus();
            return;

        }

        if (username.isEmpty()){
            editTextusername.setError("Please enter the username");
            editTextusername.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextemail.setError("Please enter the email ");
            editTextemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            editTextemail.setError("Please enter a valid email");
            editTextemail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextpassword.setError("Please enter a password ");
            editTextpassword.requestFocus();
            return;
        }

        if (!confirmpassword.equals(password)){
            editTextcpassword.setError("Password do not match");
            editTextcpassword.requestFocus();
            Toast.makeText(MainActivity.this,"Password do not match",Toast.LENGTH_SHORT).show();
            return;



        }

        Call<ResponseBody> call = RetrofitClient.getmInstance().getApi().createruser(name,username,email,password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    s = response.body().string();
                    Toast.makeText(MainActivity.this,"Sign Up Sucessful",Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });




    }

}
