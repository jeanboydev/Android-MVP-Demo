package com.jeanboy.app.mvpdemo.api.dao;


import com.jeanboy.app.mvpdemo.config.ApiConfig;
import com.jeanboy.app.mvpdemo.model.bean.UserBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Next on 2016/7/13.
 */
public interface UserDao {

    /**
     * http://blog.csdn.net/qq_17766199/article/details/49946429
     * <p>
     * Retrofit提供了5种内置的注解：GET、POST、PUT、DELETE和HEAD，在注解中指定的资源的相对URL
     *
     * @GET("users/list") 也可以在URL中指定查询参数
     * @GET("users/list?sort=desc") 请求的URL可以在函数中使用替换块和参数进行动态更新，替换块是{ and }包围的字母数字组成的字符串，
     * 相应的参数必须使用相同的字符串被@Path进行注释
     * @GET("group/{id}/users") Call<List<User>> groupList(@Path("id") int groupId);
     * <p>
     * 也可以添加查询参数
     * @GET("group/{id}/users") Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
     * <p>
     * 复杂的查询参数可以使用Map进行组合
     * @GET("group/{id}/users") Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
     * <p>
     * 可以通过@Body注解指定一个对象作为Http请求的请求体
     * @POST("users/new") Call<User> createUser(@Body User user);
     * <p>
     * 使用@FormUrlEncoded发送表单数据，使用@Field注解和参数来指定每个表单项的Key,Value为参数的值。
     * @FormUrlEncoded
     * @POST("user/edit") Call<User> getUser(@Field("name") String name, @Field("password") String password);
     * <p>
     * 使用@FormUrlEncoded发送表单数据时，表单项过多时可以使用Map进行组合
     * @FormUrlEncoded
     * @POST("user/edit") Call<User> getUser(@FieldMap Map<String, String> map);
     * <p>
     * 使用@Multipart可以进行文件上传，使用@Part指定文件路径及类型
     * @Multipart
     * @POST("/user/edit") Call<User> upload(@Part("image\"; filename=\"文件名.jpg") RequestBody file);
     * <p>
     * 使用@MapPart可以方便批量上传
     * @Multipart
     * @POST("/user/edit") Call<User> upload(@PartMap Map<String, RequestBody> params);
     * <p>
     * RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), imgFile);
     * map.put("image\"; filename=\""+imgFile.getName()+"", fileBody);
     */


    /**
     * @Body 适用范围：POST/PUT   作用：
     * @Field/@FieldMap 适用范围：POST需要FormUrlEncoded 作用：POST参数
     * @Header/@Headers/@HeaderMap 适用范围：GET    作用：头参数
     * @Multipart 适用范围：POST 搭配Part/PartMap 作用：可以方便批量上传
     * @Path 适用范围：*   作用：url参数替换
     * @Query/@QueryMap 适用范围：*  作用：url后面添加参数
     */


    @GET(ApiConfig.PATH_USERS + "/{id}")
    Call<UserBean> getInfo(@Path("id") String id);

    @POST(ApiConfig.PATH_TOKENS)
    Call<String> logIn(@Query("client") String client, @Body RequestBody params);

    @FormUrlEncoded
    @POST(ApiConfig.PATH_USERS)
    Call<String> logOut(@Field("username") String username, @Field("password") String password);
}
