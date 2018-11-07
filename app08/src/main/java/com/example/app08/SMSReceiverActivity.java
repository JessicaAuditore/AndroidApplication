package com.example.app08;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SMSReceiverActivity extends AppCompatActivity {

    private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    private boolean flag = false;
    private final String RCV_SMS_PERM = Manifest.permission.RECEIVE_SMS;
    private SMSReceiver smsReceiver = null;
    private int perm_flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsreceiver);
    }

    public void registerReceiverListener(View view) {
        if (!flag) {
            checkPermission();
            if (perm_flag != 1) {
                return;
            }
            smsReceiver = new SMSReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(SMS_ACTION);
            registerReceiver(smsReceiver, filter);
            ((Button) view).setText(getResources().getString(R.string.unregisterButton));
            flag = true;
            Toast.makeText(this, getResources().getString(R.string.registered), Toast.LENGTH_SHORT).show();
        } else {
            flag = false;
            unregisterReceiver(smsReceiver);
            ((Button) view).setText(getResources().getString(R.string.registered));
            Toast.makeText(this, getResources().getString(R.string.unregisterButton), Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, RCV_SMS_PERM) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{RCV_SMS_PERM}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, RCV_SMS_PERM)) {
                    perm_flag = -1;
                    Toast.makeText(this, getResources().getString(R.string.smsforbid), Toast.LENGTH_SHORT).show();
                    return;
                }
                return;
            }
            perm_flag = 1;
        }
    }
}
