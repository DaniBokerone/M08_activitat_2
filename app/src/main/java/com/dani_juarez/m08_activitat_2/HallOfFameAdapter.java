package com.dani_juarez.m08_activitat_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HallOfFameAdapter extends RecyclerView.Adapter<HallOfFameAdapter.ViewHolder> {

    private ArrayList<Integer> attemptsList;

    public HallOfFameAdapter(ArrayList<Integer> attemptsList) {
        this.attemptsList = attemptsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(attemptsList.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return attemptsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(int attempts, int gameNumber) {
            textView.setText("Partida " + gameNumber + ": " + attempts + " intents");
        }
    }
}
