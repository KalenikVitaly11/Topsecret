package com.example.vitaly.topsecret;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.content.Context.MODE_PRIVATE;

public class PasswordModel {
    private Context context;

    public PasswordModel(Context context) {
        this.context = context;
    }

    public void addPassword(PasswordElement passwordElement) {
        Realm.init(context);
        Realm.getDefaultInstance().executeTransaction(realm ->
                realm.insert(passwordElement)
        );
    }

    public void removePassword(PasswordElement passwordElement) {
        Realm.init(context);
        Realm.getDefaultInstance().executeTransaction(realm -> {
            RealmResults<PasswordElement> result = realm.where(PasswordElement.class)
                    .equalTo("organisation", passwordElement.getOrganisation())
                    .and()
                    .equalTo("login", passwordElement.getLogin())
                    .and()
                    .equalTo("password", passwordElement.getPassword()).findAll();
            result.deleteAllFromRealm();

        });
    }

    public ArrayList<PasswordElement> loadData() {
        ArrayList<PasswordElement> dataPassword = new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<PasswordElement> results = realm.where(PasswordElement.class).findAll();
        dataPassword.addAll(realm.copyFromRealm(results));
        return dataPassword;
    }

    public String getPin() {
        SharedPreferences sPref = context.getSharedPreferences("settings", MODE_PRIVATE);
        return sPref.getString("pin", "");
    }

    public void setPin(String firstName) {
        SharedPreferences sPref = context.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("pin", firstName);
        ed.apply();
    }

    public String getHint() {
        SharedPreferences sPref = context.getSharedPreferences("settings", MODE_PRIVATE);
        return sPref.getString("hint", "");
    }

    public void setHint(String lastName) {
        SharedPreferences sPref = context.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("hint", lastName);
        ed.apply();
    }
}
