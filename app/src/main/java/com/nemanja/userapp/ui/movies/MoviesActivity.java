package com.nemanja.userapp.ui.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nemanja.userapp.R;
import com.nemanja.userapp.data.Repository;
import com.nemanja.userapp.data.model.Movie;
import com.nemanja.userapp.ui.database.DatabaseActivity;
import com.nemanja.userapp.ui.service.ServiceActivity;
import com.nemanja.userapp.ui.volume.VolumeActivity;
import com.nemanja.userapp.util.OnCallbackMoviesRecieved;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity implements OnCallbackMoviesRecieved {

    MovieAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        recyclerView = findViewById(R.id.rv_movie_activity);

        adapter = new MovieAdapter(this, new ArrayList<Movie>());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        Repository.getInstance(this).getMovies(this);
    }

    @Override
    public void returnList(List<Movie> list) {
        adapter.updateList(list);
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


}
