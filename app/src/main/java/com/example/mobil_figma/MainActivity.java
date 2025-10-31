package com.example.mobil_figma;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView barcodeButton = findViewById(R.id.ivRight); // <- убедись, что такой id есть в XML
        if (barcodeButton != null) {
            barcodeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, BarcodeActivity.class));
                }
            });
        }

        TextView btnProfile = findViewById(R.id.btnProfile);

        // 2️⃣ Назначаем обработчик клика
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открываем активность личного кабинета
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        TextView tv = findViewById(R.id.btnSchedule);
        tv.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ScheduleActivity.class)));


        TextView btnFeedback = findViewById(R.id.btnFeedback); // это КНОПКА НА ГЛАВНОМ ЭКРАНЕ
        btnFeedback.setOnClickListener(v -> {
            new FeedbackSheet().show(getSupportFragmentManager(), "feedback");
        });


    }
}