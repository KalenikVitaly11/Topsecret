package com.example.vitaly.topsecret;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    PasswordRecyclerViewAdapter mAdapter;
    PasswordElement passwordElement;
    FloatingActionButton addPassword;
    SwipeController swipeController = null;
    TextView emptyText;

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.updateData();
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
        addPassword.setOnClickListener(this);
        setupRecyclerView();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_password:
                Intent intent = new Intent(getApplicationContext(), AddPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_passwords);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new PasswordRecyclerViewAdapter(this);
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.LEFT) {
            Log.d("myLogs", "swiped to left side");
            Realm.init(getApplicationContext());
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<PasswordElement> result = realm.where(PasswordElement.class)
                            .equalTo("organisation",mAdapter.getDataPassword().get(position).getOrganisation())
                            .and()
                            .equalTo("login", mAdapter.getDataPassword().get(position).getLogin()).findAll();
                    result.deleteAllFromRealm();
                }
            });
            mAdapter.removeElement(position);
            mAdapter.notifyItemRemoved(position);
            mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
        } else if (direction == ItemTouchHelper.RIGHT) {
            Intent intent = new Intent(this, EditPasswordActivity.class);
            intent.putExtra("organisation", mAdapter.getDataPassword().get(position).getOrganisation());
            intent.putExtra("login", mAdapter.getDataPassword().get(position).getLogin());
            intent.putExtra("password", mAdapter.getDataPassword().get(position).getPassword());
            intent.putExtra("link", mAdapter.getDataPassword().get(position).getLink());
            intent.putExtra("extraInfo", mAdapter.getDataPassword().get(position).getExtraInfo());
            startActivity(intent);
        }
        if(mAdapter.getDataPassword().isEmpty()){
            emptyText.setVisibility(View.VISIBLE);
        }
    }
}
