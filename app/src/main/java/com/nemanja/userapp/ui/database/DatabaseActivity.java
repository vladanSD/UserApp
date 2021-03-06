package com.nemanja.userapp.ui.database;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nemanja.userapp.R;
import com.nemanja.userapp.data.Repository;
import com.nemanja.userapp.data.model.User;
import com.nemanja.userapp.ui.movies.MoviesActivity;
import com.nemanja.userapp.ui.service.ServiceActivity;
import com.nemanja.userapp.ui.volume.VolumeActivity;
import com.nemanja.userapp.util.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity implements DbUserAdapter.OnSwipedListener {
    RecyclerView recyclerView;
    DbUserAdapter userAdapter;
    Repository repository;

    EditText searchEditText;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        searchEditText = findViewById(R.id.et_search_user);
        searchButton = findViewById(R.id.button_search);
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

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = searchEditText.getText().toString().trim();
                if (TextUtils.isEmpty(inputName)) {
                    searchEditText.setError("Unesite ime korisnika!");
                } else {
                    searchAction(inputName);
                }
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
            case R.id.volume:
                Intent intent2 = new Intent(this, VolumeActivity.class);
                startActivity(intent2);
                return true;
            case R.id.movies:
                Intent intent3 = new Intent(this, MoviesActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onSwiped(int index) {
        repository.deleteUser(userAdapter.getList().get(index));
    }

    public void swipeSetupAnimation() {
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(userAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    void searchAction(String inputName) {
        repository.searchUsers(inputName).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                swipeSetupAnimation();
                userAdapter.update(users);
            }
        });
    }
}
