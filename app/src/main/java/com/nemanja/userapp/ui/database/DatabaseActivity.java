package com.nemanja.userapp.ui.database;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nemanja.userapp.R;
import com.nemanja.userapp.data.Repository;
import com.nemanja.userapp.data.model.User;
import com.nemanja.userapp.ui.service.ServiceActivity;
import com.nemanja.userapp.util.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity implements DbUserAdapter.OnSwipedListener {
    RecyclerView recyclerView;
    DbUserAdapter userAdapter;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.rv_database_activity);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userAdapter = new DbUserAdapter(new ArrayList<User>(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);
        repository = Repository.getInstance(getApplicationContext());

        repository.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                swipeSetupAnimation();
                userAdapter.update(users);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.service_menu_item:
                Intent i = new Intent(this, ServiceActivity.class);
                startActivity(i);
                return true;
            case R.id.database_menu_item:
                Intent intent = new Intent(this, DatabaseActivity.class);
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onSwiped(int index) {
        repository.deleteUser(userAdapter.getList().get(index));
    }

    public void swipeSetupAnimation(){
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(userAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
