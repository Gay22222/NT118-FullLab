package com.example.Lab5_bai2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            Toast.makeText(context, "Điện thoại đang được sạc!", Toast.LENGTH_LONG).show();
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            Toast.makeText(context, "Điện thoại đã ngừng sạc!", Toast.LENGTH_LONG).show();
        }
    }
}
