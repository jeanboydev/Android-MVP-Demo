package com.isseiaoki.simplecropview.crop;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.isseiaoki.simplecropview.util.Utils;
import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseActivity;

import java.io.File;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class CropActivity extends BaseActivity {

    private static final int REQUEST_PICK_IMAGE = 10011;
    private static final int REQUEST_SAF_PICK_IMAGE = 10012;

    private CropImageView cropImageView;

    private LoadingDialog loadingDialog;

    @Override
    public Class getTag(Class clazz) {
        return CropActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crop;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        loadingDialog = new LoadingDialog(this);
        cropImageView.setCustomRatio(1, 1);
        if (cropImageView.getImageBitmap() == null) {
            cropImageView.setImageResource(R.drawable.bg);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            loadingDialog.show();
            cropImageView.startLoad(result.getData(), mLoadCallback);
        } else if (requestCode == REQUEST_SAF_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            loadingDialog.show();
            cropImageView.startLoad(Utils.ensureUriPermission(this, result), mLoadCallback);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //该操作需要Rebuild Project生成CropActivityPermissionsDispatcher
        CropActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void pickFromGallery() {//从图库选择图片
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), REQUEST_PICK_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_SAF_PICK_IMAGE);
        }
    }

    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void showRationaleForPick(PermissionRequest request) {//权限说明
        showRationaleDialog("该操作需要读取内置存储，请允许！", request);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showRationaleForCrop(PermissionRequest request) {
        showRationaleDialog("该操作需要写入内置存储，请允许！", request);
    }

    private void showRationaleDialog(String msg, final PermissionRequest request) {
        new AlertDialog.Builder(this)
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


    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void cropImage() {//裁切图片
        loadingDialog.show();
        cropImageView.startCrop(createSaveUri(), mCropCallback, mSaveCallback);
    }


    public void pickFromGallery(View v) {//从图库选择图片按钮操作
        CropActivityPermissionsDispatcher.pickFromGalleryWithCheck(this);//执行检查权限的方法
    }

    public void rotateLeft(View v) {//向左旋转
        cropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
    }

    public void rotateRight(View v) {//向右旋转
        cropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
    }

    public void done(View v) {//完成按钮
        CropActivityPermissionsDispatcher.cropImageWithCheck(this);
    }

    public Uri createSaveUri() {//获取缓存目录
        return Uri.fromFile(new File(getExternalCacheDir().getAbsolutePath(), "cropped.jpg"));
    }

    // Callbacks ///////////////////////////////////////////////////////////////////////////////////

    private final LoadCallback mLoadCallback = new LoadCallback() {
        @Override
        public void onSuccess() {
            loadingDialog.dismiss();
        }

        @Override
        public void onError() {
            loadingDialog.dismiss();
        }
    };

    private final CropCallback mCropCallback = new CropCallback() {
        @Override
        public void onSuccess(Bitmap cropped) {
        }

        @Override
        public void onError() {
        }
    };

    private final SaveCallback mSaveCallback = new SaveCallback() {
        @Override
        public void onSuccess(Uri outputUri) {
            loadingDialog.dismiss();
            Log.e("================", "====" + outputUri);
            startActivityForResult();
//            ((MainActivity) getActivity()).startResultActivity(outputUri);
        }

        @Override
        public void onError() {
            loadingDialog.dismiss();
        }
    };

    public void startResultActivity(Uri uri) {
        if (isFinishing()) return;
        // Start ResultActivity
        startActivity(ResultActivity.createIntent(this, uri));
    }
}
