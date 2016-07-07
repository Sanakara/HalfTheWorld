package database.thing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Student on 7/07/2016.
 */
public class CommentaryDAO {

    public static final String TABLE_NAME = "Commentary";
    public static final String COLUMN_COMMENTARY_ID = "cmmentaryId";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_SHOP_ID = "shopId";
    public static final String COLUMN_RATING = "rating";

    public static final String CREATE_REQUEST = "CREATE TABLE " + TABLE_NAME + "( "
            + COLUMN_COMMENTARY_ID + "INTEGER PRIMARY KEY AUTOICREMENT, "
            + COLUMN_USER_ID + " INTEGER FOREIGN KEY, "
            + COLUMN_SHOP_ID + " INTEGER FOREIGN KEY, "
            + COLUMN_RATING + " INTEGER );";

    public static final String UPGRADE_REQUEST = "DROP TABLE "+TABLE_NAME;

    private DbHelper2 dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public CommentaryDAO(Context context){ this.context = context;}

    public CommentaryDAO openWritable (){
        dbHelper = new DbHelper2(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public CommentaryDAO openReadable(){
        dbHelper = new DbHelper2(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long insert (Commentary com){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COMMENTARY_ID, com.getCommentaryId());
        cv.put(COLUMN_USER_ID, com.getUserId());
        cv.put(COLUMN_SHOP_ID, com.getShopId());
        cv.put(COLUMN_RATING, com.getRating());

        return db.insertOrThrow(TABLE_NAME, null, cv);
    }

    public Cursor getCommentaryCursorById(int commentaryId){
       Cursor c = db.query(TABLE_NAME, null, COLUMN_COMMENTARY_ID + "=" + commentaryId, null, null, null, null);
        if(c.getCount() > 0){
            c.moveToFirst();
            return c;
        }else {
            return null;
        }
    }

    public static Commentary cursorToCommentary (Cursor c){

        Commentary com = new Commentary();
        com.setCommentaryId(c.getInt(c.getColumnIndex(COLUMN_COMMENTARY_ID)));
        com.setUserId(c.getInt(c.getColumnIndex(COLUMN_USER_ID)));
        com.setShopId(c.getInt(c.getColumnIndex(COLUMN_SHOP_ID)));
        com.setRating(c.getInt(c.getColumnIndex(COLUMN_RATING)));

        return com;
    }

    public Commentary getCommentaryById(int commentaryId){

        Cursor c = getCommentaryCursorById(commentaryId);
        if(c != null){
            Commentary com = cursorToCommentary(c);
            return com;
        } else {
            return null;
        }
    }

    public void update(Commentary com){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COMMENTARY_ID, com.getCommentaryId());
        cv.put(COLUMN_USER_ID, com.getUserId());
        cv.put(COLUMN_SHOP_ID, com.getShopId());
        cv.put(COLUMN_RATING, com.getRating());

        db.update(TABLE_NAME, cv, COLUMN_COMMENTARY_ID + "=" + com.getCommentaryId(), null);
    }

    public void delete (Commentary com){
        db.delete(TABLE_NAME, COLUMN_COMMENTARY_ID + "=" + com.getCommentaryId(), null);
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }




}
