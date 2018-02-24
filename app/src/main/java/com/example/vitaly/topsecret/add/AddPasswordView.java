package com.example.vitaly.topsecret.add;


import com.example.vitaly.topsecret.PasswordElement;

public interface AddPasswordView {
    PasswordElement getPassword();

    boolean checkData();

    void completed();
}
