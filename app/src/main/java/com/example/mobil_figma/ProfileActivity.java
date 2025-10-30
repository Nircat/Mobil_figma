package com.example.mobil_figma;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProfileActivity extends AppCompatActivity {

    private TextInputEditText etLast, etFirst, etMiddle, etMember, etPass;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefs = getSharedPreferences("profile", MODE_PRIVATE);

        // --- findViewById с проверками ---
        etLast   = findViewById(R.id.etLastName);
        etFirst  = findViewById(R.id.etFirstName);
        etMiddle = findViewById(R.id.etMiddleName);
        etMember = findViewById(R.id.etMemberNumber);
        etPass   = findViewById(R.id.etPassNumber);

        ImageView ivClose = findViewById(R.id.ivClose);
        if (ivClose != null) {
            ivClose.setOnClickListener(v -> finish());
        }

        // Дефолты
        String defLast   = "Батталов";
        String defFirst  = "Роман";
        String defMiddle = "Вадимович";
        String defPass   = "24";

        // Твой номер из prefs, иначе сгенерировать и сохранить
        String memberId = prefs.getString("member_id", null);
        if (memberId == null || memberId.isEmpty()) {
            memberId = randomDigits(12);
            prefs.edit().putString("member_id", memberId).apply();
        }

        // Подставляем значения (проверяем на null чтобы не упасть)
        if (etLast != null)   etLast.setText(prefs.getString("last",   defLast));
        if (etFirst != null)  etFirst.setText(prefs.getString("first",  defFirst));
        if (etMiddle != null) etMiddle.setText(prefs.getString("middle", defMiddle));
        if (etMember != null) {
            etMember.setText(memberId);
            etMember.setEnabled(false); // read-only по макету
            etMember.setTextColor(Color.WHITE);
        }
        if (etPass != null)   etPass.setText(prefs.getString("passnum", defPass));

        // Сохранение по потере фокуса — только если поле найдено
        setSaveOnFocusLost(etLast,  "last");
        setSaveOnFocusLost(etFirst, "first");
        setSaveOnFocusLost(etMiddle,"middle");
        setSaveOnFocusLost(etPass,  "passnum");

        // История посещений
        RecyclerView rv = findViewById(R.id.rvVisits);
        if (rv != null) {
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new VisitAdapter(dummyVisits()));
        }

        Toast.makeText(this, "Профиль открыт", Toast.LENGTH_SHORT).show();
    }

    private void setSaveOnFocusLost(@Nullable TextInputEditText et, String key) {
        if (et == null) return;
        et.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                CharSequence txt = et.getText();
                prefs.edit().putString(key, txt == null ? "" : txt.toString()).apply();
            }
        });
    }

    private String randomDigits(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(r.nextInt(10));
        return sb.toString();
    }

    private List<Visit> dummyVisits() {
        List<Visit> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Visit("19.03.2025", "8.00 - 11.10"));
        }
        return list;
    }
}
