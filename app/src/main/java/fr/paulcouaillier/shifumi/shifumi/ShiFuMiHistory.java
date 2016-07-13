package fr.paulcouaillier.shifumi.shifumi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import fr.paulcouaillier.shifumi.Helper;

/**
 * Created by paulcouaillier on 20/06/16.
 */
public class ShiFuMiHistory {

    protected static List<ShiFuMiTurn> history;

    private final Helper helper;

    private final SQLiteDatabase sqLiteDatabase;

    public ShiFuMiHistory(Context context) {
        helper = new Helper(context);
        sqLiteDatabase = helper.getWritableDatabase();
        if(history == null) {
            this.loadFromSQLITE(context);
        }
    }

    public void add(final ShiFuMiTurn shiFuMiTurn) {
        ContentValues values = new ContentValues();
        values.put(Helper.COLUMN_USER_CHOICE, shiFuMiTurn.getUserInput());
        values.put(Helper.COLUMN_COMPUTER_CHOICE, shiFuMiTurn.getComputerInput());
        values.put(Helper.COLUMN_SELECTED_CHOICE, shiFuMiTurn.getChoice());
        Long id = sqLiteDatabase.insert(Helper.TABLE_SHIFUMITURN, null, values);
        shiFuMiTurn.setId(id.intValue());
        ShiFuMiHistory.history.add(shiFuMiTurn);
    }

    private class SQLiteLoader extends AsyncTask<Context, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Context... contexts) {
            List<ShiFuMiTurn> shiFuMiTurns = new ArrayList<>();
            Cursor cursor = sqLiteDatabase.query(Helper.TABLE_SHIFUMITURN, null, null, null, null, null, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                shiFuMiTurns.add(
                        new ShiFuMiTurn(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getInt(2),
                                cursor.getInt(3)));
                cursor.moveToNext();
            }
            cursor.close();
            ShiFuMiHistory.history = shiFuMiTurns;
            return true;
        }
    }

    private void loadFromSQLITE(Context context) {
        new SQLiteLoader().execute(context);
    }
}
