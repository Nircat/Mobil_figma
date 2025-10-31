package com.example.mobil_figma;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

public class FeedbackSheet extends BottomSheetDialogFragment {

    private TextInputEditText etMessage;

    @NonNull
    @Override
    public BottomSheetDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View content = LayoutInflater.from(getContext()).inflate(R.layout.sheet_feedback, null, false);
        dialog.setContentView(content);

        etMessage = content.findViewById(R.id.etMessage);
        View btnSend = content.findViewById(R.id.btnSend);

        // Разворачиваем до нужной высоты (не на весь экран)
        content.post(() -> {
            View parent = (View) content.getParent();
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(parent);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED); // можно убрать, если не нужно
            behavior.setSkipCollapsed(true);
        });

        // Фокус и клавиатура
        content.post(() -> {
            etMessage.requestFocus();
            InputMethodManager imm = (InputMethodManager)
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) imm.showSoftInput(etMessage, InputMethodManager.SHOW_IMPLICIT);
        });

        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText() == null ? "" : etMessage.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                etMessage.setError("Пустое сообщение");
                return;
            }

            // TODO: отправка на сервер/почту/Firestore по твоему выбору
            Toast.makeText(requireContext(), "Отправлено: " + text, Toast.LENGTH_SHORT).show();

            // Закрыть и очистить
            etMessage.getText().clear();
            dismiss();
        });

        return dialog;
    }

    @Override
    public void onDismiss(@NonNull android.content.DialogInterface dialog) {
        super.onDismiss(dialog);
        // Прячем клавиатуру
        try {
            InputMethodManager imm = (InputMethodManager)
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            View v = getDialog() != null ? getDialog().getCurrentFocus() : null;
            if (imm != null && v != null) imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception ignored) {}
    }
}
