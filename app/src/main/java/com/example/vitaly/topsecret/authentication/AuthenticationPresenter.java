package com.example.vitaly.topsecret.authentication;


import com.example.vitaly.topsecret.PasswordModel;

class AuthenticationPresenter {
    private AuthenticationView view;
    private PasswordModel model;

    AuthenticationPresenter(PasswordModel model, AuthenticationView view){
        this.model = model;
        this.view = view;
    }

    void init(){
        if(model.getPin().isEmpty()){
            view.goToRegistration();
        } else {
            view.goToLogin();
        }
    }
}
