package com.pdmi.app.wepage.util.permisson;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

abstract class PermissionRequest {
    private ArrayList<String> permissions;
    private WeakReference<Activity> activity;
    private WeakReference<DevicePermissionResultListener> listener;
    private int requestCode;

    PermissionRequest(Activity activity, DevicePermissionResultListener listener, int requestCode) {
        permissions = new ArrayList<>();
        this.activity = new WeakReference<Activity>(activity);
        this.listener = new WeakReference<DevicePermissionResultListener>(listener);
        this.requestCode = requestCode;
    }

    public ArrayList<String> getPermissions() {
        providePermissions(permissions);
        return permissions;
    }

    protected abstract void providePermissions(List<String> permissions);

    protected abstract int getRationalText();

    public Activity getActivity() {
        return activity.get();
    }

    public DevicePermissionResultListener getListener() {
        return listener.get();
    }

    public int getRequestCode() {
        return requestCode;
    }
}
