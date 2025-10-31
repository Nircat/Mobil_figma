package com.example.mobil_figma;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleGridAdapter extends RecyclerView.Adapter<ScheduleGridAdapter.VH> {

    private final List<Cell> items;

    public ScheduleGridAdapter(List<Cell> items) {
        this.items = items;
    }

    static class VH extends RecyclerView.ViewHolder {
        final TextView tv;
        VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvCell);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule_cell, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Cell item = items.get(position);
        h.tv.setText(item.text);

        switch (item.type) {
            case HEADER:
                h.tv.setBackgroundResource(R.drawable.bg_cell_header);
                h.tv.setTextSize(15f);
                h.tv.setTypeface(h.tv.getTypeface(), Typeface.BOLD);
                break;
            case TIME:
                h.tv.setBackgroundResource(R.drawable.bg_cell_header);
                h.tv.setTextSize(16f);
                h.tv.setTypeface(h.tv.getTypeface(), Typeface.BOLD);
                break;
            case DATA:
                h.tv.setBackgroundResource(R.drawable.bg_cell);
                h.tv.setTextSize(14f);
                h.tv.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }

    @Override
    public int getItemCount() { return items.size(); }
}
