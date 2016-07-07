package database.thing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 04/07/2016.
 */
public class DbHelper2 extends SQLiteOpenHelper{

    public static final String DB_NAME = "half_the_world_db";
    public static final int DB_VERSION= 1;

    public DbHelper2(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.CREATE_REQUEST);
        Log.e("DATA_CREATE", "true");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserDAO.UPGRADE_REQUEST);
        onCreate(db);

    }



}
