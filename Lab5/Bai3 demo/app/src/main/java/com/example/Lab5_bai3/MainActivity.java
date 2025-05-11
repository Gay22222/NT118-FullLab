package com.example.Lab5_bai3;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private String sender = "";
    private static final String PREFS_NAME = "AutoResponsePrefs";
    private static final String AUTO_RESPONSE_KEY = "auto_response";

    // Lưu trạng thái của Auto response
    private void saveAutoResponseState(boolean state) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(AUTO_RESPONSE_KEY, state);
        editor.apply();
    }

    // Lấy trạng thái của Auto response
    private boolean getAutoResponseState() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return sharedPreferences.getBoolean(AUTO_RESPONSE_KEY, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.RECEIVE_SMS, android.Manifest.permission.READ_SMS
                        , Manifest.permission.SEND_SMS},
                1);

        LinearLayout buttonResponses = findViewById(R.id.btnResponses);
        TextView txtMessage = findViewById(R.id.txtMessage);
        Button btnSafe = findViewById(R.id.btnSafe);
        Button btnLove = findViewById(R.id.btnLove);
        Switch switchAuto = findViewById(R.id.switchAuto);

        // Nhận thông tin từ SmsReceiver
        handleIncomingIntent(getIntent());

        // Xử lý nút phản hồi
        btnSafe.setOnClickListener(v -> sendSms(sender, "I am fine and safe. Worry not!"));
        btnLove.setOnClickListener(v -> sendSms(sender, "Tell my mother I love her"));

        // Xử lý Switch Auto response
        switchAuto.setChecked(getAutoResponseState()); // Khởi tạo trạng thái từ SharedPreferences
        switchAuto.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveAutoResponseState(isChecked); // Lưu trạng thái vào SharedPreferences
            Toast.makeText(this, "Auto response: " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();

            if (isChecked) buttonResponses.setVisibility(View.GONE);
            else buttonResponses.setVisibility(View.VISIBLE);
        });
    }

    private void sendSms(String recipient, String message) {
        if (recipient != null) {
            Toast.makeText(this, "Sending SMS to: " + recipient, Toast.LENGTH_SHORT).show();

        }

        try {
            // Sử dụng SmsManager để gửi tin nhắn
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(recipient, null, message, null, null);

            // Hiển thị thông báo gửi thành công
            Toast.makeText(this, "SMS sent to: " + recipient, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Hiển thị thông báo lỗi nếu có lỗi xảy ra
            Toast.makeText(this, "Failed to send SMS: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void handleIncomingIntent(Intent intent) {
        String newSender = intent.getStringExtra("sender");
        if (newSender != null && !newSender.isEmpty()) {
            sender = newSender;
            TextView txtMessage = findViewById(R.id.txtMessage);
            txtMessage.setText("Received SMS from: " + sender);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent); // Cập nhật Intent mới
        handleIncomingIntent(intent); // Xử lý nội dung tin nhắn mới
    }

}