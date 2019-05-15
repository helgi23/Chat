package com.example.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {


    ArrayList<String> mesages;
    LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<String> mesages) {
        this.mesages = mesages;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_message, parent, false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String msg = mesages.get(position);
        viewHolder.message.setText(msg);

    }

    @Override
    public int getItemCount() {
        return mesages.size();
    }
}
