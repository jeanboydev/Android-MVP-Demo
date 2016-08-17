package com.jeanboy.app.api.file;

import com.jeanboy.app.model.bean.FileBean;
import com.jeanboy.manager.net.NetManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Next on 2016/8/17.
 */
public class FileApi {

    private FileDao fileDao;

    public FileApi() {
        fileDao = NetManager.getInstance().create(FileDao.class);
    }

    public Call<FileBean> upload(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        return fileDao.upload(part);
    }
}
