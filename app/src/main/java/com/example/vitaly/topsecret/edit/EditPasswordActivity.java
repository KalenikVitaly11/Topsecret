package com.example.vitaly.topsecret.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.vitaly.topsecret.PasswordElement;
import com.example.vitaly.topsecret.PasswordModel;
import com.example.vitaly.topsecret.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class EditPasswordActivity extends AppCompatActivity implements EditView {
    MaterialEditText organisation;
    MaterialEditText login;
    MaterialEditText password;
    MaterialEditText extraInfo;
    MaterialEditText link;

    PasswordElement editableElement;

    private EditPresenter presenter;

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


        PasswordModel model = new PasswordModel(this);
        presenter = new EditPresenter(model, this);
        presenter.loadFromIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.finish_editing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.finish_editing:
                presenter.done();
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

    @Override
    public void loadFromIntent() {
        Intent intent = getIntent();
        organisation.setText(intent.getStringExtra("organisation"));
        login.setText(intent.getStringExtra("login"));
        password.setText(intent.getStringExtra("password"));
        extraInfo.setText(intent.getStringExtra("extraInfo"));
        link.setText(intent.getStringExtra("link"));

        editableElement = new PasswordElement();
        editableElement.setOrganisation(intent.getStringExtra("organisation"));
        editableElement.setLogin(intent.getStringExtra("login"));
        editableElement.setPassword(intent.getStringExtra("password"));
    }

    @Override
    public PasswordElement getEditablePassword() {
        return editableElement;
    }
}
