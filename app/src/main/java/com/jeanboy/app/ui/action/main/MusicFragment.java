package com.jeanboy.app.ui.action.main;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseFragment;
import com.jeanboy.cropview.cropper.CropperHandler;
import com.jeanboy.cropview.cropper.CropperManager;
import com.jeanboy.cropview.cropper.CropperParams;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MusicFragment extends BaseFragment implements View.OnClickListener, CropperHandler {

    @BindView(R.id.btn_from_camera)
    Button btn_from_camera;
    @BindView(R.id.btn_from_gallery)
    Button btn_from_gallery;


    public MusicFragment() {
    }

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public void setupArguments(Bundle args) {
    }

    @Override
    public void setupView(View view, Bundle savedInstanceState) {
        btn_from_camera.setOnClickListener(this);
        btn_from_gallery.setOnClickListener(this);

        CropperManager.getInstance().build(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_from_camera:
                MusicFragmentPermissionsDispatcher.pickFromCameraWithCheck(this);
                break;
            case R.id.btn_from_gallery:
                MusicFragmentPermissionsDispatcher.pickFromGalleryWithCheck(this);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropperManager.getInstance().handlerResult(requestCode, resultCode, data);
    }

    @Override
    public CropperParams getParams() {
        return new CropperParams(1, 1);
    }

    @Override
    public void onCropped(Uri uri) {
        Log.e("=====onCropped======", "=============" + uri);
    }

    @Override
    public void onCropCancel() {
        Log.e("=====onCropCancel====", "+++");
    }

    @Override
    public void onCropFailed(String msg) {
        Log.e("=====onCropFailed===", "=============" + msg);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MusicFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void pickFromCamera() {
        CropperManager.getInstance().pickFromCamera();
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void pickFromGallery() {
        CropperManager.getInstance().pickFromGallery();
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog("该操作需要使用摄像头，请允许！", request);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void showRationaleForPick(PermissionRequest request) {//权限说明
        showRationaleDialog("该操作需要操作内存卡，请允许！", request);
    }


    private void showRationaleDialog(String msg, final PermissionRequest request) {
        new AlertDialog.Builder(getActivity())
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(msg)
                .show();
    }
}
