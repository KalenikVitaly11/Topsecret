package com.example.vitaly.topsecret.storage;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.vitaly.topsecret.add.AddPasswordActivity;
import com.example.vitaly.topsecret.edit.EditPasswordActivity;
import com.example.vitaly.topsecret.PasswordElement;
import com.example.vitaly.topsecret.PasswordModel;
import com.example.vitaly.topsecret.PasswordRecyclerViewAdapter;
import com.example.vitaly.topsecret.R;
import com.example.vitaly.topsecret.RecyclerItemTouchHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, MainView {
    PasswordRecyclerViewAdapter mAdapter;
    FloatingActionButton addPassword;
    TextView emptyText;
    RecyclerView recyclerView;
    ArrayList<PasswordElement> dataPassword;
    private CoordinatorLayout coordinatorLayout;
    private MainPresenter presenter;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadData();
        if (mAdapter.getDataPassword().isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
        } else {
            emptyText.setVisibility(View.GONE);
        }
        Log.d("myLogs", "onResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity_title);
        emptyText = (TextView) findViewById(R.id.empty_db);
        addPassword = (FloatingActionButton) findViewById(R.id.add_password);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_passwords);

        addPassword.setOnClickListener(clickedView ->
                presenter.goToAdd()
        );

        PasswordModel model = new PasswordModel(this);
        presenter = new MainPresenter(model, this);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.LEFT) {
            presenter.remove(position, mAdapter.getDataPassword().get(position));
        } else if (direction == ItemTouchHelper.RIGHT) {
            presenter.edit(position);
        }
        if (mAdapter.getDataPassword().isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goToAdd() {
        Intent intent = new Intent(getApplicationContext(), AddPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void loadData(ArrayList<PasswordElement> dataPassword) {
        this.dataPassword = dataPassword;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PasswordRecyclerViewAdapter(this, dataPassword);
        recyclerView.setAdapter(mAdapter);
        mAdapter.sort();
        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(recyclerView);
    }

    @Override
    public void swipeEdit(int position) {
        Intent intent = new Intent(this, EditPasswordActivity.class);
        intent.putExtra("organisation", mAdapter.getDataPassword().get(position).getOrganisation());
        intent.putExtra("login", mAdapter.getDataPassword().get(position).getLogin());
        intent.putExtra("password", mAdapter.getDataPassword().get(position).getPassword());
        intent.putExtra("link", mAdapter.getDataPassword().get(position).getLink());
        intent.putExtra("extraInfo", mAdapter.getDataPassword().get(position).getExtraInfo());
        startActivity(intent);
    }

    @Override
    public void swipeRemove(int position) {
        PasswordElement passwordElement = new PasswordElement(mAdapter.getDataPassword().get(position).getOrganisation(), mAdapter.getDataPassword().get(position).getLogin(),
                mAdapter.getDataPassword().get(position).getPassword(),
                mAdapter.getDataPassword().get(position).getExtraInfo(), mAdapter.getDataPassword().get(position).getLink());

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, mAdapter.getDataPassword().get(position).getLogin() + " удален", Snackbar.LENGTH_LONG);

        mAdapter.removeElement(position);

        snackbar.setAction("Отменить", view ->
            presenter.add(passwordElement)
        );
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }

    @Override
    public void add(PasswordElement passwordElement) {
        mAdapter.addElement(passwordElement);
    }
}
