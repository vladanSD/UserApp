package com.nemanja.userapp.ui.database;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nemanja.userapp.R;
import com.nemanja.userapp.data.model.User;

import java.util.List;

public class DbUserAdapter extends RecyclerView.Adapter<DbUserAdapter.ViewHolder> implements com.nemanja.userapp.util.OnSwipedListener {
    private List<User> list;
    private OnSwipedListener listener;

    public DbUserAdapter(List<User> list, OnSwipedListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public DbUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_database_user, parent, false);
        return new DbUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DbUserAdapter.ViewHolder holder, int position) {
        final User user = list.get(position);

        holder.nameTextView.setText(user.getName());
        holder.numberTextView.setText("+381 " + String.valueOf(user.getNumber()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:+381" + user.getNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<User> getList() {
        return list;
    }

    @Override
    public void onItemDismiss(int position) {
        listener.onSwiped(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView numberTextView;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name_db);
            numberTextView = itemView.findViewById(R.id.tv_number_db);
            context = itemView.getContext();
        }
    }

    interface OnSwipedListener {
        void onSwiped(int index);
    }

}
