package com.example.mobil_figma;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleGridDividerDecoration extends RecyclerView.ItemDecoration {
    private final Paint p;

    public ScheduleGridDividerDecoration(Context ctx) {
        p = new Paint();
        p.setColor(0x33FFFFFF); // полупрозрачные линии
        p.setStrokeWidth(ctx.getResources().getDisplayMetrics().density); // ~1dp
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = parent.getChildAt(i);
            // правая
            c.drawLine(v.getRight(), v.getTop(), v.getRight(), v.getBottom(), p);
            // нижняя
            c.drawLine(v.getLeft(), v.getBottom(), v.getRight(), v.getBottom(), p);
        }
    }
}
