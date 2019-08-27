package com.jihan.mini_core.delegates;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.callback.CallBackManager;
import com.jihan.mini_core.callback.CallBackType;
import com.jihan.mini_core.callback.IGlobalCallBack;
import com.jihan.mini_core.ui.camera.CameraImageBean;
import com.jihan.mini_core.ui.camera.MiniCamera;
import com.jihan.mini_core.ui.camera.RequestCodes;
import com.jihan.mini_core.ui.scanner.ScannerDelegate;
import com.jihan.mini_core.util.MiniLogger;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Jihan on 2019/8/9
 */

@RuntimePermissions
public abstract class PermissionsCheckerDelegate extends BaseDelegate {

    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        MiniCamera.start(this);
    }

    public void startCameraWithCheck() {
        PermissionsCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void startScanner(BaseDelegate delegate) {
        delegate.getSupportDelegate().startForResult(new ScannerDelegate(),RequestCodes.SCAN);
    }

    public void startScannerWithCheck(BaseDelegate delegate){
        PermissionsCheckerDelegatePermissionsDispatcher.startScannerWithPermissionCheck(this,delegate);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        Mini.showToast("不允许拍照");
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        Mini.showToast("永久拒绝权限");
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCodes.TAKE_PHOTO: {
                final Uri uri = CameraImageBean.getInstance().getPath();
                UCrop.of(uri, uri)
                        .withMaxResultSize(400, 400)
                        .start(getContext(), this);
                break;
            }
            case RequestCodes.PICK_PHOTO: {
                if(data != null){
                    final Uri uri = data.getData();
                    final String pickPhotoPath = MiniCamera.createCropFile().getPath();
                    UCrop.of(uri,Uri.parse(pickPhotoPath))
                            .withMaxResultSize(400,400)
                            .start(getContext(),this);
                }
                break;
            }
            case RequestCodes.CROP_PHOTO: {
                if(data != null) {
                    final Uri uri = UCrop.getOutput(data);
                    IGlobalCallBack<Uri> callBack = CallBackManager
                            .getInstance()
                            .getCallBack(CallBackType.ON_CROP);
                    if (callBack != null) {
                        callBack.execute(uri);
                    }
                }else{
                    Mini.showToast("取消裁剪");
                }
                break;
            }
            case RequestCodes.SCAN: {
                break;
            }
            default:
                break;
        }
    }
}
