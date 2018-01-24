package com.nemanja.userapp.ui.service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nemanja.userapp.R;
import com.nemanja.userapp.data.model.User;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> list;
    private OnButtonClicked clickListener;

    public UserAdapter(List<User> list, OnButtonClicked clickListener) {
        this.list = list;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_service_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = list.get(position);

        holder.nameTextView.setText(user.getName());
        holder.numberTextView.setText("+381 "+String.valueOf(user.getNumber()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<User> getList(){
        return list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView nameTextView;
            TextView numberTextView;
            Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            numberTextView = itemView.findViewById(R.id.tv_number);
            button =itemView.findViewById(R.id.b_add);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onAddButtonClicked(getAdapterPosition());
        }
    }

    interface OnButtonClicked{
        void onAddButtonClicked(int index);
    }
}
