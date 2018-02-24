package com.example.vitaly.topsecret.rename;


import com.example.vitaly.topsecret.PasswordModel;

class LoginPresenter {
    private int failedAttempts = 0;
    private PasswordModel model;
    private LoginView view;

    String pin;

    LoginPresenter(PasswordModel model, LoginView view){
        this.model = model;
        this.view = view;
    }

    private void checkPin(String text){
        if(text.length() == 4){
            if(text.equals(model.getPin())){
                view.goToStorage();
            } else {
                view.clearPin();
                failedAttempts++;
                if(failedAttempts >= 5){
                    view.showHint();
                }
            }
        }
    }

    void clickedBackspace(String text){
        if(!text.isEmpty()) {
            view.setPinText(text.substring(0, text.length() - 1));
        }
    }

    void clicked(String text){
        view.setPinText(text);
        checkPin(text);
    }


}
