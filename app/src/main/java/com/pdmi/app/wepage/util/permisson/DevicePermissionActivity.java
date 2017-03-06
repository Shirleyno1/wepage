package com.pdmi.app.wepage.util.permisson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class DevicePermissionActivity extends AppCompatActivity {
    public static final String PERMISSIONS = "permissions";
    public static final String REQUEST_CODE = "request_code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            handleIntent(getIntent());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        ArrayList<String> permissions = intent.getStringArrayListExtra(PERMISSIONS);
        int requestCode = intent.getIntExtra(REQUEST_CODE, -1);
        ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        DevicePermissionCenter.dispatchRequestPermissionsResult(requestCode,permissions,grantResults);
        finish();
    }

    public static void startPermissionActivity(Activity act, ArrayList<String> permissions, int requestCode) {
        if (act == null) {
            return;
        }
        Intent intent = new Intent(act, DevicePermissionActivity.class);
        intent.putStringArrayListExtra(PERMISSIONS, permissions);
        intent.putExtra(REQUEST_CODE, requestCode);
        act.startActivity(intent);
    }
}

