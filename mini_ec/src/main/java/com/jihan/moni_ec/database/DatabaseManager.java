package com.jihan.moni_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author Jihan
 * @date 2019/8/13
 */
public class DatabaseManager {

    private DaoSession mDaoSession;
    private UserProfileDao mUserDao;

    private DatabaseManager(){}

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    public UserProfileDao getDao(){
        return mUserDao;
    }

    public static DatabaseManager getInstance(){
        return Holder.DATABASE_MANAGER;
    }

    private static final class Holder {
        private static final DatabaseManager DATABASE_MANAGER = new DatabaseManager();
    }

    private void initDao(Context context){
        ReleaseOpenHelper openHelper = new ReleaseOpenHelper(context,"mini_ec.db");
        Database db = openHelper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mUserDao = mDaoSession.getUserProfileDao();
    }
}
