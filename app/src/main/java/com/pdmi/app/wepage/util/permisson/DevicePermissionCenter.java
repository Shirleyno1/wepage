package com.pdmi.app.wepage.util.permisson;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.HashMap;
import java.util.ListIterator;

public class DevicePermissionCenter {
    public static final int REQUEST_CODE_CONTACT = 180;
    public static final int REQUEST_CODE_CAMERA = 181;
    public static final int REQUEST_CODE_RECORD_AUDIO = 182;
    public static final int REQUEST_CODE_STORAGE = 183;
    public static final int REQUEST_CODE_PHONE = 184;
    private static DevicePermissionCenter instance;
    private HashMap<Integer, PermissionRequest> requestMap;

    DevicePermissionCenter() {
        requestMap = new HashMap<>();
    }

    private static DevicePermissionCenter getInstance() {
        if (instance == null) {
            synchronized (DevicePermissionCenter.class) {
                if (instance == null) {
                    instance = new DevicePermissionCenter();
                }
            }
        }
        return instance;
    }

    public static void request(PermissionRequest request) {
        getInstance()._request(request);
    }

    private void _request(PermissionRequest request) {
        requestMap.put(request.getRequestCode(), request);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callWithPermission(request);
            return;
        }

        boolean hasPermissions = true;
        boolean showRationale = false;
        Activity act = request.getActivity();
        if (null == act) {
            callNoPermission(request);
            return;
        }
        //check each permission
        for (ListIterator<String> iterator = request.getPermissions().listIterator(); iterator.hasNext(); ) {
            String permission = iterator.next();
            if (ContextCompat.checkSelfPermission(act, permission) != PackageManager.PERMISSION_GRANTED) {
                hasPermissions = false;
            } else {
                iterator.remove();
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(act, permission)) {
                showRationale = true;
            }
        }
        //all permissions already granted
        if (hasPermissions) {
            callWithPermission(request);
            return;
        }

        //some permission not granted
        if (showRationale) {
            showRationalDialog(request);
        } else {
            Log.d("Permission", "request Permission:" + request.getPermissions());
            startPermissionActivity(request);
        }
    }

    private void showRationalDialog(final PermissionRequest request) {
        Activity act = request.getActivity();
        if (null == act) {
            callNoPermission(request);
            return;
        }
        Log.e("Permission", "request Permission:" + request.getPermissions());
        startPermissionActivity(request);
//        MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(act);
//        dialogBuilder.content(act.getText(request.getRationalText()))
//                .cancelable(false)
//                .callback(new MaterialDialog.ButtonCallback() {
//                    @Override
//                    public void onPositive(MaterialDialog dialog) {
//                        super.onPositive(dialog);
//                        Log.d("Permission", "request Permission:" + request.getPermissions());
//                        startPermissionActivity(request);
//                    }
//
//                    @Override
//                    public void onNegative(MaterialDialog dialog) {
//                        callNoPermission(request);
//                    }
//                })
////                .positiveText(R.string.confirm)
////                .negativeText(R.string.cancel)
//                .show();
    }

    private void startPermissionActivity(PermissionRequest request) {
        Activity act = request.getActivity();
        if (null == act) {
            callNoPermission(request);
            return;
        }
        DevicePermissionActivity.startPermissionActivity(act, request.getPermissions(), request.getRequestCode());
    }

    private void callNoPermission(PermissionRequest request) {
        DevicePermissionResultListener listener = request.getListener();
        if (listener != null) {
            listener.onRequestPermissionsRejected(request.getRequestCode());
        }
        requestMap.remove(request.getRequestCode());
    }

    private void callWithPermission(PermissionRequest request) {
        DevicePermissionResultListener listener = request.getListener();
        if (listener != null) {
            listener.onRequestPermissionsGranted(request.getRequestCode());
        }
        requestMap.remove(request.getRequestCode());
    }

    public static void dispatchRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        getInstance()._onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void _onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionRequest request = requestMap.get(requestCode);
        if (request != null) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        Log.d("Permission", "Permission rejected.");
                        callNoPermission(request);
                        return;
                    }
                }
                Log.d("Permission", "Permission granted.");
                callWithPermission(request);

            } else {
                Log.d("Permission", "Permission rejected.");
                callNoPermission(request);
            }
        }
    }
}
