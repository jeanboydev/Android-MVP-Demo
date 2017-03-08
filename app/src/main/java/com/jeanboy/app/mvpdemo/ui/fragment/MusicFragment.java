package com.jeanboy.app.mvpdemo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseFragment;

import butterknife.BindView;

public class MusicFragment extends BaseFragment implements View.OnClickListener {

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


    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_from_camera:
//                MusicFragmentPermissionsDispatcher.pickFromCameraWithCheck(this);
//                break;
//            case R.id.btn_from_gallery:
//                MusicFragmentPermissionsDispatcher.pickFromGalleryWithCheck(this);
//                break;
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        MusicFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }
//
//    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void pickFromCamera() {
//        CropperManager.getInstance().pickFromCamera();
//    }
//
//    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void pickFromGallery() {
//        CropperManager.getInstance().pickFromGallery();
//    }
//
//    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void showRationaleForCamera(PermissionRequest request) {
//        showRationaleDialog("该操作需要使用摄像头，请允许！", request);
//    }
//
//    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void showRationaleForPick(PermissionRequest request) {//权限说明
//        showRationaleDialog("该操作需要操作内存卡，请允许！", request);
//    }
//
//
//    private void showRationaleDialog(String msg, final PermissionRequest request) {
//        new AlertDialog.Builder(getActivity())
//                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(@NonNull DialogInterface dialog, int which) {
//                        request.proceed();
//                    }
//                })
//                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(@NonNull DialogInterface dialog, int which) {
//                        request.cancel();
//                    }
//                })
//                .setCancelable(false)
//                .setMessage(msg)
//                .show();
//    }
}
