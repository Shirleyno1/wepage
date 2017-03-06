package com.pdmi.app.wepage.util.permisson;

public interface DevicePermissionResultListener {
    void onRequestPermissionsGranted(int requestCode);

    void onRequestPermissionsRejected(int requestCode);
}
