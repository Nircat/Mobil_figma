package com.example.mobil_figma;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // --- элементы интерфейса ---
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabs = findViewById(R.id.tabLayout);
        ViewPager2 pager = findViewById(R.id.viewPager);

        // --- настройка тулбара ---
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // --- адаптер страниц ---
        pager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return SchedulePageFragment.newInstance(position);
            }

            @Override
            public int getItemCount() {
                return 2; // две таблицы
            }
        });

        // --- связка вкладок с ViewPager ---
        new TabLayoutMediator(tabs, pager, (tab, pos) -> {
            String title = (pos == 0) ? "Таблица 1" : "Таблица 2";
            tab.setText(title);
            tab.setContentDescription(title); // важно для озвучки
        }).attach();

        // --- цвет статус-бара в бежевый (по желанию) ---
        getWindow().setStatusBarColor(getColor(R.color.Bej));
    }
}
