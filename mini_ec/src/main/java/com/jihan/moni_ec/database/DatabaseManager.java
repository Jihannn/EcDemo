package com.jihan.moni_ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jihan
 * @date 2019/8/27
 */
public class DatabaseManager {

    private DaoSession mDaoSession = null;

    private DatabaseManager() {
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "ec_demo.db");
        final SQLiteDatabase db = helper.getWritableDatabase();
        final DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public UserProfileDao getUserDao(){
        return mDaoSession.getUserProfileDao();
    }
}
