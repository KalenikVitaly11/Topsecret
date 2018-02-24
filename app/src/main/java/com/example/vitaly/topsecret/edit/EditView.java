package com.example.vitaly.topsecret.edit;


import com.example.vitaly.topsecret.PasswordElement;

public interface EditView {
    PasswordElement getPassword();

    boolean checkData();

    void completed();

    void loadFromIntent();

    PasswordElement getEditablePassword();
}
