package com.jeanboy.cropview.cropper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import com.jeanboy.cropview.util.Utils;

/**
 * Created by Next on 2016/8/3.
 */
public class CropperManager {


    public static final String MSG_FILE_NOT_FOUND = "图片不可用";
    public static final String MSG_ERROR = "操作失败";

    private static CropperManager instance;

    private CropperHandler cropperHandler;

    private CropperManager() {
    }

    public static CropperManager getInstance() {
        if (instance == null) {
            synchronized (CropperManager.class) {
                if (instance == null) {
                    instance = new CropperManager();
                }
            }
        }
        return instance;
    }

    public void build(CropperHandler cropperHandler) {
        this.cropperHandler = cropperHandler;
    }

    public void pickFromCamera() {
        if (cropperHandler == null) return;
        cropperHandler.getContext().startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).
                        putExtra(MediaStore.EXTRA_OUTPUT, ""),// FIXME: 拍照保存路径
                CropperParams.REQUEST_PICK_CAMERA);

    }

    public void pickFromGallery() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            cropperHandler.getContext().startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                    CropperParams.REQUEST_PICK_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            cropperHandler.getContext().startActivityForResult(intent, CropperParams.REQUEST_KITKAT_PICK_IMAGE);
        }
    }

    public void handlerResult(int requestCode, int resultCode, Intent result) {
        if (cropperHandler == null) return;
        if (resultCode == Activity.RESULT_CANCELED) {
            cropperHandler.onCropCancel();
        } else if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CropperParams.REQUEST_PICK_CAMERA:// FIXME: 相机拍完照回调处理，去裁切
                    break;
                case CropperParams.REQUEST_PICK_IMAGE:
                    if (result != null) {
                        cropperHandler.getContext().startActivityForResult(new Intent(cropperHandler.getContext(), CropActivity.class)
                                        .putExtra(CropperParams.PICK_URI, result.getData())
                                        .putExtra(CropperParams.ASPECT_X, cropperHandler.getParams().aspectX)
                                        .putExtra(CropperParams.ASPECT_Y, cropperHandler.getParams().aspectY),
                                CropperParams.REQUEST_CROPPED);
                    } else {
                        cropperHandler.onCropFailed(MSG_FILE_NOT_FOUND);
                    }
                    break;
                case CropperParams.REQUEST_KITKAT_PICK_IMAGE:
                    if (result != null) {
                        cropperHandler.getContext().startActivityForResult(new Intent(cropperHandler.getContext(), CropActivity.class)
                                        .putExtra(CropperParams.PICK_URI, Utils.ensureUriPermission(cropperHandler.getContext(), result))
                                        .putExtra(CropperParams.ASPECT_X, cropperHandler.getParams().aspectX)
                                        .putExtra(CropperParams.ASPECT_Y, cropperHandler.getParams().aspectY),
                                CropperParams.REQUEST_CROPPED);
                    } else {
                        cropperHandler.onCropFailed(MSG_FILE_NOT_FOUND);
                    }
                    break;
                case CropperParams.REQUEST_CROPPED:
                    if (result != null) {
                        cropperHandler.onCropped(Uri.parse(result.getStringExtra(CropperParams.PICK_URI)));
                    } else {
                        cropperHandler.onCropFailed(MSG_ERROR);
                    }
                    break;
            }
        }
    }
}
