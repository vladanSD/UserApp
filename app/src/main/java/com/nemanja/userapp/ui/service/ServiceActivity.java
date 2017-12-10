package com.nemanja.userapp.ui.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nemanja.userapp.R;
import com.nemanja.userapp.data.Repository;
import com.nemanja.userapp.data.model.User;
import com.nemanja.userapp.ui.database.DatabaseActivity;
import com.nemanja.userapp.util.OnCallbackRecieved;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity implements OnCallbackRecieved, UserAdapter.OnButtonClicked {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Repository repository;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_main_activity);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userAdapter = new UserAdapter(new ArrayList<User>(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);

        repository = Repository.getInstance(getApplicationContext());
        repository.getUsersWithService(this);
    }

    @Override
    public void returnList(List<User> list) {
        userAdapter.update(list);

    }

    @Override
    public void onAddButtonClicked(int index) {
        repository.addUserToDb(userAdapter.getList().get(index));
        if(toast != null) toast.cancel();
        toast = Toast.makeText(this, "User added to db", Toast.LENGTH_SHORT);
        toast.show();
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
}
