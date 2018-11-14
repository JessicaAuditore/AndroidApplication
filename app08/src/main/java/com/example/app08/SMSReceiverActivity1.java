package com.example.app08;

import android.Manifest;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;


public class SMSReceiverActivity1 extends AppCompatActivity {

    private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private boolean flag = false;
    private final String RCV_SMS_PERM = Manifest.permission.RECEIVE_SMS;
    private SMSReceiver smsReceiver = null;
    private int perm_flag = 2;//0:许可，-1拒绝不再询问，-2 拒绝可以再询问
    private Button myButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsreceiver);
    }

    public void registerReceiverListener(View v) {
        if (!flag) {
            myButton = (Button) v;

            if (perm_flag != 0 && perm_flag != -1) {
                checkPermission();
            }

            if (perm_flag == 0) {
                register();
            } else if (perm_flag == -1) {
                Toast.makeText(this, getResources().getString(R.string.smsforbid), Toast.LENGTH_SHORT).show();
            }

        } else {
            flag = false;
            unregisterReceiver(smsReceiver);
            ((Button) v).setText(this.getResources().getString(R.string.registerButton));
            Toast.makeText(this, getResources().getString(R.string.unregistered), Toast.LENGTH_SHORT).show();
        }
    }

    void checkPermission() {
        perm_flag = ActivityCompat.checkSelfPermission(this, RCV_SMS_PERM);
        if (perm_flag != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{RCV_SMS_PERM}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                perm_flag = -2;
                //用户勾选了不再询问
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, RCV_SMS_PERM)) {
                    perm_flag = -1;
                    Toast.makeText(this, getResources().getString(R.string.smsforbid), Toast.LENGTH_SHORT).show();
                    return;
                }
                return;
            } else {
                perm_flag = 0;
            }
        }
    }

    protected void register() {
        smsReceiver = new SMSReceiver();
        IntentFilter filter = new IntentFilter(SMS_ACTION);
        registerReceiver(smsReceiver, filter);
        myButton.setText(getResources().getString(R.string.unregisterButton));
        flag = true;
        Toast.makeText(this, getResources().getString(R.string.registered), Toast.LENGTH_SHORT).show();
    }
}

