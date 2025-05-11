package com.example.Lab5_bai1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Build;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver smsReceiver;
    private IntentFilter smsFilter;
    private TextView txtShowMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtShowMess = findViewById(R.id.tv_content);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS},
                1);

        initBroadcastReceiver();
    }

    private void initBroadcastReceiver() {
        smsFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }

    private void processReceive(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message), Toast.LENGTH_LONG).show();

        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus == null || pdus.length == 0) return;

        String format = bundle.getString("format");
        StringBuilder smsBuilder = new StringBuilder();

        for (Object pdu : pdus) {
            SmsMessage message;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                message = SmsMessage.createFromPdu((byte[]) pdu, format);
            } else {
                message = SmsMessage.createFromPdu((byte[]) pdu);
            }
            String sender = message.getDisplayOriginatingAddress();
            String content = message.getMessageBody();
            smsBuilder.append(sender).append(":\n").append(content).append("\n");
        }

        txtShowMess.setText(smsBuilder.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (smsReceiver != null) registerReceiver(smsReceiver, smsFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (smsReceiver != null) unregisterReceiver(smsReceiver);
    }
}