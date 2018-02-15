package com.example.vitaly.topsecret;


import android.app.Activity;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public abstract class PreferencesHelper {
    public static String getPin(Activity activity){
        SharedPreferences sPref = activity.getSharedPreferences("settings", MODE_PRIVATE);
        String authToken = sPref.getString("pin", "");
        return authToken;
    }

    public static void setPin(Activity activity, String firstName){
        SharedPreferences sPref = activity.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("pin", firstName);
        ed.apply();
    }

    public static String getHint(Activity activity){
        SharedPreferences sPref = activity.getSharedPreferences("settings", MODE_PRIVATE);
        String authToken = sPref.getString("hint", "");
        return authToken;
    }

    public static void setHint(Activity activity, String lastName){
        SharedPreferences sPref = activity.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("hint", lastName);
        ed.apply();
    }
}
