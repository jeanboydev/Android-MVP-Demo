package com.jeanboy.app.mvpdemo.ui.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseFragment;
import com.jeanboy.app.mvpdemo.ui.activity.LoginActivity;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.btn_from_gallery)
    Button btn_from_gallery;
    @BindView(R.id.btn_login)
    Button btn_login;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void setupArguments(Bundle args) {

    }

    @Override
    public void setupView(View view, Bundle savedInstanceState) {

        btn_from_gallery.setOnClickListener(this);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_from_gallery:
                pickFromGallery();
                break;
            case R.id.btn_login:
                (getActivity()).startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }


    public static final int REQUEST_PICK_IMAGE = 10011;
    public static final int REQUEST_KITKAT_PICK_IMAGE = 10012;

    private void pickFromGallery() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            getActivity().startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                    REQUEST_PICK_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            getActivity().startActivityForResult(intent, REQUEST_KITKAT_PICK_IMAGE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//
//                case REQUEST_PICK_IMAGE:
//                    if (data != null) {
//                        Uri uri = data.getData();
//                        compressImage(uri);
//                    } else {
//                        Log.e("======", "========图片为空======");
//                    }
//                    break;
//                case REQUEST_KITKAT_PICK_IMAGE:
//                    if (data != null) {
//                        Uri uri = Utils.ensureUriPermission(getActivity(), data);
//                        compressImage(uri);
//                    } else {
//                        Log.e("======", "====-----==图片为空======");
//                    }
//                    break;
//            }
//        }
    }

//    public void compressImage(Uri uri) {
//        Log.e("===compressImage===", "====开始====uri==" + uri.getPath());
//        try {
//            File saveFile = new File(getActivity().getExternalCacheDir(), "compress_" + System.currentTimeMillis() + ".jpg");
//            File saveFinalFile = new File(getActivity().getExternalCacheDir(), "compress_final_" + System.currentTimeMillis() + ".jpg");
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
//
////            Log.e("===compressImage===", "====开始==JNI==saveFile==" + saveFile.getAbsolutePath());
////            NativeUtil.compressBitmap(bitmap, saveFile.getAbsolutePath(), true);
////            Log.e("===compressImage===", "====完成==JNI==saveFile==" + saveFile.getAbsolutePath());
//
//
//            Log.e("===compressImage===", "====开始==终极==saveFile==" + saveFinalFile.getAbsolutePath());
//            NativeUtil.compressBitmap(bitmap, saveFinalFile.getAbsolutePath());
//            Log.e("===compressImage===", "====完成==终极==saveFile==" + saveFinalFile.getAbsolutePath());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
