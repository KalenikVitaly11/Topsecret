package com.example.vitaly.topsecret.registration;


import com.example.vitaly.topsecret.PasswordModel;

class RegistrationPresenter {
    private final RegistrationView view;
    private PasswordModel model;

    RegistrationPresenter(RegistrationView registrationView, PasswordModel model) {
        this.view = registrationView;
        this.model = model;
    }

    void goToStorage(){
        if(view.checkData()){
            model.setPin(view.getPin());
            model.setHint(view.getHint());
            view.openStorageScreen();
        }
    }
}
