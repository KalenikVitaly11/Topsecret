package com.example.vitaly.topsecret;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.rengwuxian.materialedittext.MaterialEditText;

import io.realm.Realm;

public class AddPasswordActivity extends AppCompatActivity {

    MaterialEditText organisation;
    MaterialEditText login;
    MaterialEditText password;
    MaterialEditText extraInfo;
    MaterialEditText link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);
        setTitle(R.string.add_password);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        organisation = (MaterialEditText) findViewById(R.id.add_organisation);
        login = (MaterialEditText) findViewById(R.id.add_login);
        password = (MaterialEditText) findViewById(R.id.add_password);
        extraInfo = (MaterialEditText) findViewById(R.id.add_extra_info);
        link = (MaterialEditText) findViewById(R.id.add_link);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.finish_editing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
                        realm.insert(passwordElement);
                    });
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
