package com.jihan.moni_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author Jihan
 * @date 2019/8/13
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
