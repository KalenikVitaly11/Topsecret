package com.example.vitaly.topsecret.add;


import android.util.Log;

import com.example.vitaly.topsecret.PasswordModel;

public class AddPasswordPresenter {
    private AddPasswordView view;
    private PasswordModel model;

    AddPasswordPresenter(PasswordModel model, AddPasswordView view){
        this.view = view;
        this.model = model;
    }

    public void add(){
        if(view.checkData()) {
            model.addPassword(view.getPassword());
            Log.d("myLogs", "Added");
            view.completed();
        }
    }
}
