//package com.jeanboy.app.mvpdemo.model.sources.local;
//
//import android.support.annotation.NonNull;
//
//import com.jeanboy.app.mvpdemo.model.bean.UserBean;
//import com.jeanboy.app.mvpdemo.common.utils.Page;
//import com.jeanboy.manager.database.DataManager;
//
//import java.util.List;
//
///**
// * 数据库获取应该使用LoaderManager配合Loader来获取数据，
// * 数据的保存，更新，删除的操作交给DBManager
// * <p>
// * Created by Next on 2016/7/5.
// */
//public class UserLocalDataSource implements UserLocal {
//
//    private static UserLocalDataSource INSTANCE;
//
//    private DataManager mDataManager;
//
//    public static UserLocalDataSource getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new UserLocalDataSource();
//        }
//        return INSTANCE;
//    }
//
//
//    @Override
//    public boolean save(@NonNull UserBean userBean) {
//        return false;
//    }
//
//    @Override
//    public boolean delete(@NonNull String id) {
//        return false;
//    }
//
//    @Override
//    public boolean clear() {
//        return false;
//    }
//
//    @Override
//    public boolean update(@NonNull UserBean userBean) {
//        return false;
//    }
//
//    @Override
//    public UserBean get(@NonNull String id) {
//        return null;
//    }
//
//    @Override
//    public List<UserBean> load(Page page) {
//        return null;
//    }
//}
