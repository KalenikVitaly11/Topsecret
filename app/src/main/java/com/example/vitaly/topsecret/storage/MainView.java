package com.example.vitaly.topsecret.storage;


import com.example.vitaly.topsecret.PasswordElement;

import java.util.ArrayList;

public interface MainView {
    void goToAdd();

    void loadData(ArrayList<PasswordElement> dataPassword);

    void swipeEdit(int position);

    void swipeRemove(int position);

    void add(PasswordElement passwordElement);
}
