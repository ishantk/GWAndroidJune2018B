package com.auribises.gw2018b;

import android.net.Uri;

public class Util {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Users.db";

    public static final String TAB_NAME = "User";

    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_EMAIL = "EMAIL";

    public static final String CREATE_TAB_QUERY = "create table User(" +
            "_ID integer primary key autoincrement," +
            "NAME varchar(256)," +
            "PHONE varchar(256)," +
            "EMAIL varchar(256)" +
            ")";

    public static final Uri USER_URI = Uri.parse("content://com.auribises.gw2018b.mycp/"+TAB_NAME);
}
