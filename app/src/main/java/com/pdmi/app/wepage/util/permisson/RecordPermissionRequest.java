package com.pdmi.app.wepage.util.permisson;

import android.Manifest;
import android.app.Activity;

import com.pdmi.app.wepage.R;

import java.util.ArrayList;
import java.util.List;

public class RecordPermissionRequest extends PermissionRequest {

    public RecordPermissionRequest(Activity activity, DevicePermissionResultListener listener) {
        super(activity, listener, DevicePermissionCenter.REQUEST_CODE_RECORD_AUDIO);
    }

    @Override
    protected void providePermissions(List<String> permissions) {
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        if (permissions.size() == 0) {
            permissions.add(Manifest.permission.RECORD_AUDIO);
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected int getRationalText() {
        return R.string.permission_reason_record;
    }

}
