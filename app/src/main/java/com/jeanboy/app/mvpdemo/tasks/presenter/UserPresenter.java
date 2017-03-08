//package com.jeanboy.app.mvpdemo.tasks.presenter;
//
//import android.support.annotation.NonNull;
//
//import com.jeanboy.app.mvpdemo.model.bean.TokenBean;
//import com.jeanboy.app.mvpdemo.model.bean.UserBean;
//import com.jeanboy.app.mvpdemo.tasks.contract.UserContract;
//import com.jeanboy.lib.common.manager.net.RequestCallback;
//
//import retrofit2.Call;
//import retrofit2.Response;
//
//import static com.google.common.base.Preconditions.checkNotNull;
//
///**
// * Presenter管理View逻辑，调用Repository
// *
// * Created by Next on 2016/7/4.
// */
//public class UserPresenter implements UserContract.Presenter {
//
//    private UserRepository mUserRepository;
//
//    private final UserContract.View mUserView;
//
//
//    private boolean mFirstLoad = true;
//
//    public UserPresenter(@NonNull UserContract.View userView) {
//        mUserView = checkNotNull(userView, "userView cannot be null!");
//        mUserRepository = UserRepository.getInstance();
//    }
//
//
//    @Override
//    public Call<TokenBean> logIn(String username, String password, final RequestCallback<TokenBean> callback) {
//        mUserView.setLoadingIndicator(true);
//        return mUserRepository.logIn(username, password, new RequestCallback<TokenBean>() {
//            @Override
//            public void success(Response<TokenBean> response) {
//                mUserRepository.refresh();//用户登陆，需要重新刷新缓存信息
//                mUserView.setLoadingIndicator(false);
//                callback.success(response);
//            }
//
//            @Override
//            public void error(String msg) {
//                mUserView.setLoadingIndicator(false);
//                mUserView.toast(msg);
//                callback.error(msg);
//            }
//        });
//    }
//
//    @Override
//    public Call<UserBean> logOut(String username, String password, final RequestCallback<UserBean> callback) {
//        mUserView.setLoadingIndicator(true);
//        return mUserRepository.logOut(username, password, new RequestCallback<UserBean>() {
//            @Override
//            public void success(Response<UserBean> response) {
//                mUserView.setLoadingIndicator(false);
//                callback.success(response);
//            }
//
//            @Override
//            public void error(String msg) {
//                mUserView.setLoadingIndicator(false);
//                mUserView.toast(msg);
//                callback.error(msg);
//            }
//        });
//    }
//
//    @Override
//    public Call<UserBean> getInfo(String id, final RequestCallback<UserBean> callback) {
//        mUserView.setLoadingIndicator(true);
//        return mUserRepository.getInfo(id, new RequestCallback<UserBean>() {
//            @Override
//            public void success(Response<UserBean> response) {
//                mUserView.setLoadingIndicator(false);
//                callback.success(response);
//            }
//
//            @Override
//            public void error(String msg) {
//                mUserView.setLoadingIndicator(false);
//                mUserView.toast(msg);
//                callback.error(msg);
//            }
//        });
//    }
//}