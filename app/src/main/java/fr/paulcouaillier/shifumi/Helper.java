package fr.paulcouaillier.shifumi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Helper extends SQLiteOpenHelper {

    public static final String DATABASE = "SHIFUMI";

    public static final String TABLE_SHIFUMITURN = "SHIFUMITURN";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_USER_CHOICE = "USERCHOICE";
    public static final String COLUMN_COMPUTER_CHOICE = "COMPUTERCHOICE";
    public static final String COLUMN_SELECTED_CHOICE = "SHIFUMITURN";

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_SHIFUMI_CREATE = "create table "
            + TABLE_SHIFUMITURN + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_COMPUTER_CHOICE + " integer not null,"
            + COLUMN_USER_CHOICE+ " integer not null, "
            + COLUMN_SELECTED_CHOICE + " integer not null);";

    public Helper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_SHIFUMI_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(Helper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE);;
        onCreate(sqLiteDatabase);
    }
}
