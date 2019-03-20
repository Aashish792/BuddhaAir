package com.example.buddhaair.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.buddhaair.data.User;

public class SharedPreferenceManager {

    private static  final String Shared_Pref_Name = "new_shared_pref";

    private static SharedPreferenceManager mInstance;
    private static Context mcontext;

    private SharedPreferenceManager(Context mcontext){

        this.mcontext = mcontext;

    }

    public static  synchronized SharedPreferenceManager getmInstance(Context mcontext){
        if(mInstance == null){
            mInstance =  new SharedPreferenceManager(mcontext);
        }
        return mInstance;
    }

    public void saveuser(User user){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id",user.getId());
        editor.putString("name",user.getName());
        editor.putString("username",user.getUsername());
        editor.putString("email",user.getEmail());
        editor.putString("password",user.getPassword());
        editor.apply();
    }

    public boolean isLoggedIn(){

        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1)!= -1;
    }
    //
    public User getUser(){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id",-1),
                sharedPreferences.getString("name",null),
                sharedPreferences.getString("username",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("password",null),
                sharedPreferences.getBoolean("error",false)
        );


    }

    public void clear(){

        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
