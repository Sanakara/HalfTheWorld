package be.sankara.halftheworld.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.sankara.halftheworld.model.User;

/**
 * Created by User on 04/07/2016.
 */
public class UserDAO {

    public static final String TABLE_NAME = "User";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public static final String CREATE_REQUEST= "CREATE TABLE "+ UserDAO.TABLE_NAME
            + " ( "+ COLUMN_USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, "
            +COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, "
            +COLUMN_PASSWORD + " TEXT NOT NULL "+");";

    public static final String UPGRADE_REQUEST = "DROP TABLE " + TABLE_NAME;

    private DbHelper2 dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public UserDAO (Context context){
        this.context = context;
    }

    public UserDAO openWritable(){
        dbHelper = new DbHelper2(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public UserDAO openReadable(){
        dbHelper = new DbHelper2(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long insert (User u){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, u.getUserName());
        cv.put(COLUMN_EMAIL, u.getEmail());
        cv.put(COLUMN_PASSWORD, u.getPassword());

        return db.insertOrThrow(TABLE_NAME, null, cv);

    }

    public Cursor getUserCursorByUsername(String username){
        Cursor c = db.query(TABLE_NAME, null, COLUMN_USERNAME + " = '" + username+ "'", null, null, null, null);
        if(c.getCount() > 0){
            c.moveToFirst();
            return c;
        }else {
            return null;
        }
    }


    public Cursor getUserCursorById(int userId){
        Cursor c = db.query(TABLE_NAME, null, COLUMN_USER_ID + " = '" + userId + "'", null, null, null, null);
        if(c.getCount() > 0){
            c.moveToFirst();
            return c;
        }else {
            return null;
        }
    }

    public static User cursorToUser(Cursor c){
        User u = new User();
        u.setUserName(c.getString(c.getColumnIndex(COLUMN_USERNAME)));
        u.setEmail(c.getString(c.getColumnIndex(COLUMN_EMAIL)));
        u.setPassword(c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
        return u;
    }

    public User getUserByUsername(String username){
        Cursor c = getUserCursorByUsername(username);
        if(c != null){
            User u = cursorToUser(c);
            return u;
        }else {
            return null;
        }
    }

    public User getUserById(int userId){
        Cursor c = getUserCursorById(userId);
        if(c != null){
            User u = cursorToUser(c);
            return u;
        }else {
            return null;
        }
    }

    public void  update (User u){
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUMN_USERNAME, u.getUserName());
        cv1.put(COLUMN_EMAIL, u.getEmail());
        cv1.put(COLUMN_PASSWORD, u.getPassword());

        db.update(TABLE_NAME, cv1, COLUMN_USER_ID + "=" + u.getUserId(), null);

    }

    public void delete(User u){
        db.delete(TABLE_NAME, COLUMN_USER_ID + "=" + u.getUserId(), null );
    }


    public SQLiteDatabase getDatabase() {
        return db;
    }
}
