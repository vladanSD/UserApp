package com.nemanja.userapp.ui.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nemanja.userapp.R;
import com.nemanja.userapp.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> list = new ArrayList<>();

    public MovieAdapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        final Movie movie = list.get(position);
        holder.bind(movie.getTitle(), movie.getPosterPath());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Movie> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieName;
        ImageView movieImage;

        public ViewHolder(View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.tv_movie_name);
            movieImage = itemView.findViewById(R.id.iv_movie_image);
        }

        public void bind(String title, String posterPath) {

            movieName.setText(title);
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + posterPath).into(movieImage);
        }
    }
}
