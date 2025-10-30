package com.example.mobil_figma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.VH> {

    private final List<Visit> data;

    public VisitAdapter(List<Visit> data) { this.data = data; }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvDate, tvTime;
        VH(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvVisitDate);
            tvTime = itemView.findViewById(R.id.tvVisitTime);
        }
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_visit, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        Visit v = data.get(pos);
        h.tvDate.setText(v.date);
        h.tvTime.setText(v.time);
    }

    @Override public int getItemCount() { return data.size(); }
}
