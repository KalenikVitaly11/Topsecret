package com.example.vitaly.topsecret.storage;


import com.example.vitaly.topsecret.PasswordElement;
import com.example.vitaly.topsecret.PasswordModel;

public class MainPresenter {
    private PasswordModel model;
    private MainView view;

    MainPresenter(PasswordModel model, MainView view){
        this.model = model;
        this.view = view;
    }

    void remove(int position, PasswordElement element){
        view.swipeRemove(position);
        model.removePassword(element);
    }

    void edit(int position){
        view.swipeEdit(position);
    }

    void goToAdd(){
        view.goToAdd();
    }

    void loadData(){
        view.loadData(model.loadData());
    }

    public void add(PasswordElement passwordElement){
        view.add(passwordElement);
        model.addPassword(passwordElement);
    }
}
