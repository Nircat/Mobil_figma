package com.example.mobil_figma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SchedulePageFragment extends Fragment {

    private static final String ARG_PAGE = "page";

    public static SchedulePageFragment newInstance(int page) {
        SchedulePageFragment f = new SchedulePageFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_PAGE, page);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int page = getArguments() != null ? getArguments().getInt(ARG_PAGE, 0) : 0;

        List<List<Cell>> matrix = (page == 0)
                ? ScheduleData.table1()
                : ScheduleData.table2();

        RecyclerView rv = view.findViewById(R.id.recycler);
        int spanCount = ScheduleData.NUM_COLS;

        ScheduleGridAdapter adapter = new ScheduleGridAdapter(ScheduleData.flatten(matrix));
        rv.setLayoutManager(new GridLayoutManager(requireContext(), spanCount));
        rv.setAdapter(adapter);
        rv.addItemDecoration(new ScheduleGridDividerDecoration(requireContext()));
    }
}
