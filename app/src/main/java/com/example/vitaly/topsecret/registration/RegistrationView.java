package com.example.vitaly.topsecret.registration;


public interface RegistrationView {
    boolean checkData();

    void openStorageScreen();

    String getPin();

    String getHint();
}
