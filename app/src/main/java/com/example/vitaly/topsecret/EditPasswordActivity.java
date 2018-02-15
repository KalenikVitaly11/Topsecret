package com.example.vitaly.topsecret;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.rengwuxian.materialedittext.MaterialEditText;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditPasswordActivity extends AppCompatActivity {
    MaterialEditText organisation;
    MaterialEditText login;
    MaterialEditText password;
    MaterialEditText extraInfo;
    MaterialEditText link;

    String organisationText;
    String loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        setTitle(R.string.edit_password);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        organisation = (MaterialEditText) findViewById(R.id.edit_organisation);
        login = (MaterialEditText) findViewById(R.id.edit_login);
        password = (MaterialEditText) findViewById(R.id.edit_password);
        extraInfo = (MaterialEditText) findViewById(R.id.edit_extra_info);
        link = (MaterialEditText) findViewById(R.id.edit_link);

        Intent intent = getIntent();
        organisation.setText(intent.getStringExtra("organisation"));
        login.setText(intent.getStringExtra("login"));
        password.setText(intent.getStringExtra("password"));
        extraInfo.setText(intent.getStringExtra("extraInfo"));
        link.setText(intent.getStringExtra("link"));

        organisationText = intent.getStringExtra("organisation");
        loginText = intent.getStringExtra("login");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.finish_editing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.finish_editing:
                if (organisation.getText().toString().isEmpty()) {
                    organisation.setError("Надо заполнить");
                } else if (login.getText().toString().isEmpty()) {
                    login.setError("Надо заполнить");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Надо заполнить");
                } else {
                    PasswordElement passwordElement = new PasswordElement(organisation.getText().toString(), login.getText().toString(), password.getText().toString(),
                            extraInfo.getText().toString(), link.getText().toString());
                    Realm.init(this);
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        RealmResults<PasswordElement> result = realm.where(PasswordElement.class)
                                .equalTo("organisation", organisationText)
                                .and()
                                .equalTo("login", loginText).findAll();
                        result.deleteAllFromRealm();
                        realm.insert(passwordElement);
                    });
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
