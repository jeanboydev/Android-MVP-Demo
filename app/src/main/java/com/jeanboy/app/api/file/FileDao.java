package com.jeanboy.app.api.file;

import com.jeanboy.app.model.bean.FileBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Next on 2016/8/17.
 */
public interface FileDao {

    @Multipart
    @POST("upload")
    Call<FileBean> upload(@Part("file") MultipartBody.Part file);

    @Multipart
    @POST("upload")
    Call<FileBean> upload(@PartMap Map<String, RequestBody> params);

}
