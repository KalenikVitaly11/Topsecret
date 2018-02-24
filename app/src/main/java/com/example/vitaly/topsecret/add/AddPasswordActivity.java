package com.example.vitaly.topsecret.add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.vitaly.topsecret.PasswordElement;
import com.example.vitaly.topsecret.PasswordModel;
import com.example.vitaly.topsecret.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class AddPasswordActivity extends AppCompatActivity implements AddPasswordView {

    MaterialEditText organisation;
    MaterialEditText login;
    MaterialEditText password;
    MaterialEditText extraInfo;
    MaterialEditText link;

    private AddPasswordPresenter presenter;

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

        PasswordModel model = new PasswordModel(this);
        presenter = new AddPasswordPresenter(model, this);
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
                presenter.add();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public PasswordElement getPassword() {
        PasswordElement passwordElement = new PasswordElement(organisation.getText().toString(), login.getText().toString(), password.getText().toString(),
                extraInfo.getText().toString(), link.getText().toString());
        return passwordElement;
    }

    @Override
    public boolean checkData() {
        if (organisation.getText().toString().isEmpty()) {
            organisation.setError("Надо заполнить");
            return false;
        }
        if (login.getText().toString().isEmpty()) {
            login.setError("Надо заполнить");
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Надо заполнить");
            return false;
        }
        return true;
    }

    @Override
    public void completed() {
        finish();
    }
}