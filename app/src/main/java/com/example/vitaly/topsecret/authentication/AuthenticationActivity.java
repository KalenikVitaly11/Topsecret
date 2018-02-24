package com.example.vitaly.topsecret.authentication;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vitaly.topsecret.rename.LoginFragment;
import com.example.vitaly.topsecret.PasswordModel;
import com.example.vitaly.topsecret.R;
import com.example.vitaly.topsecret.registration.RegistrationFragment;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationView{

    PasswordModel model;
    AuthenticationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        model = new PasswordModel(this);
        presenter = new AuthenticationPresenter(model, this);
        presenter.init();
    }

    @Override
    public void goToRegistration() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RegistrationFragment registrationFragment = new RegistrationFragment();
        fragmentTransaction.replace(R.id.content, registrationFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void goToLogin() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.content, loginFragment);
        fragmentTransaction.commit();
    }
}
