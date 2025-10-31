package com.example.mobil_figma;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;
import android.view.View;
import android.content.SharedPreferences;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;



public class BarcodeActivity extends AppCompatActivity {

    private TextView tvFullName;
    private TextView tvMemberId;
    private ImageView ivBarcode;

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_code);

        View root = findViewById(R.id.root);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Даём отступ всему экрану: сверху под статус-бар, снизу под жестовую панель
            v.setPadding(0, bars.top, 0, bars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });


        tvFullName = findViewById(R.id.tvFullName);
        tvMemberId = findViewById(R.id.tvMemberId);
        ivBarcode  = findViewById(R.id.ivBarcode);
        ivBack     = findViewById(R.id.ivBack);

        updateUiFromPrefs();

        // Данные из личного кабинета (замени на реальные)
        String fullName = "Батталов Роман Вадимович";
        String memberId = generateRandomId(12);
        getSharedPreferences("profile", MODE_PRIVATE)
                .edit()
                .putString("member_id", memberId)
                .apply();


        tvFullName.setText(fullName);
        tvMemberId.setText("Номер карты: " + memberId);

        try {
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bmp = encoder.encodeBitmap(memberId, BarcodeFormat.CODE_128, 900, 300);
            ivBarcode.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); // просто закрывает экран и возвращает на MainActivity
                }
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.topBar), (v, insets) -> {
            int top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            v.setPadding(0, top, 0, 0);
            return insets;
        });


    }

    protected void onResume() {
        super.onResume();
        updateUiFromPrefs();   // на случай возврата на экран
    }


    private String generateRandomId(int length) {
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < length; i++) {
            sb.append(digits.charAt(random.nextInt(digits.length())));
        }
        return sb.toString();
    }

    private String randomDigits(int len) {
        java.util.Random r = new java.util.Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) sb.append(r.nextInt(10));
        return sb.toString();
    }

    private void updateUiFromPrefs() {
        SharedPreferences p = getSharedPreferences("profile", MODE_PRIVATE);

        // 1) ФИО: берём из ЛК, с дефолтами
        String last   = p.getString("last",   "Батталов");
        String first  = p.getString("first",  "Роман");
        String middle = p.getString("middle", "Вадимович");
        String fullName = (last + " " + first + " " + middle)
                .replaceAll("\\s+", " ").trim();
        tvFullName.setText(fullName);

        // 2) Твой номер: берём сохранённый, иначе генерим и сохраняем
        String memberId = p.getString("member_id", null);
        if (memberId == null || memberId.isEmpty()) {
            memberId = randomDigits(12);
            p.edit().putString("member_id", memberId).apply();
        }
        tvMemberId.setText("Номер карты: " + memberId);

        // 3) Рендерим штрих-код по текущему номеру
        try {
            BarcodeEncoder enc = new BarcodeEncoder();
            Bitmap bmp = enc.encodeBitmap(memberId, BarcodeFormat.CODE_128, 900, 300);
            ivBarcode.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
