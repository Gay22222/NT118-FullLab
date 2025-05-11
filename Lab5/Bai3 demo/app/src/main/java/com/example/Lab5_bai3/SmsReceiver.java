package com.example.Lab5_bai3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.content.SharedPreferences;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        String format = bundle.getString("format");
        if (pdus == null || pdus.length == 0) return;

        for (Object pdu : pdus) {
            SmsMessage smsMessage;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                smsMessage = SmsMessage.createFromPdu((byte[]) pdu, format);
            } else {
                smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
            }

            String sender = smsMessage.getDisplayOriginatingAddress();
            String message = smsMessage.getDisplayMessageBody();

            if (message != null && message.toLowerCase().contains("are you ok")) {
                Toast.makeText(context, "Received SMS from: " + sender, Toast.LENGTH_SHORT).show();

                SharedPreferences prefs = context.getSharedPreferences("AutoResponsePrefs", Context.MODE_PRIVATE);
                boolean isAuto = prefs.getBoolean("auto_response", false);

                if (isAuto) {
                    sendAutoResponse(context, sender);
                } else {
                    Intent mainIntent = new Intent(context, MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mainIntent.putExtra("sender", sender);
                    context.startActivity(mainIntent);
                }
            }
        }
    }

    private void sendAutoResponse(Context context, String recipient) {
        if (recipient == null || recipient.trim().isEmpty()) return;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            String response = "I am fine and safe. Worry not!";
            smsManager.sendTextMessage(recipient, null, response, null, null);

            Toast.makeText(context, "Auto-response sent to: " + recipient, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Failed to send auto-response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
