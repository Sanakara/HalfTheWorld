package database.thing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Student on 7/07/2016.
 */
public class ShopDAO {

    public static final String TABLE_NAME = "Shop";
    public static final String COLUMN_SHOP_ID = "shopId";
    public static final String COLUMN_SHOP_NAME = "shopName";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_RATING = "rating";

    public static final String CREATE_REQUEST = "CREATE_TABLE "+TABLE_NAME+"("
            +COLUMN_SHOP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_SHOP_NAME+" TEXT NOT NULL, "
            +COLUMN_LOCATION+" TEXT NOT NULL, "
            +COLUMN_RATING+" INTEGER NOT NULL );";

    public static final String UPGRADE_REQUEST = "DROP TABLE "+TABLE_NAME;

    private DbHelper2 dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public ShopDAO(Context context){ this.context = context;}

    public ShopDAO openWritable (){
        dbHelper = new DbHelper2(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public ShopDAO openReadable(){
        dbHelper = new DbHelper2(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long insert (Shop s){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SHOP_NAME, s.getShopName());
        cv.put(COLUMN_LOCATION, s.getLocation());
        cv.put(COLUMN_RATING, s.getRating());

        return db.insertOrThrow(TABLE_NAME, null, cv);
    }

    public Cursor getShopCursorById(int shopId) {
        Cursor c = db.query(TABLE_NAME, null, COLUMN_SHOP_ID + "=" + shopId, null, null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            return c;
        } else {
            return null;
        }
    }

    public static Shop cursorToShop(Cursor c){
        Shop s = new Shop();
        s.setShopId(c.getInt(c.getColumnIndex(COLUMN_SHOP_ID)));
        s.setShopName(c.getString(c.getColumnIndex(COLUMN_SHOP_NAME)));
        s.setLocation(c.getString(c.getColumnIndex(COLUMN_LOCATION)));
        s.setRating(c.getInt(c.getColumnIndex(COLUMN_RATING)));

        return s;
    }

    public Shop getShopById(int shopId){
        Cursor c = getShopCursorById(shopId);
        if(c != null){
            Shop s = cursorToShop(c);
            return s;
        }else {
            return null;
        }
    }

    public void update (Shop s){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SHOP_NAME, s.getShopName());
        cv.put(COLUMN_LOCATION, s.getLocation());
        cv.put(COLUMN_RATING, s.getRating());

        db.update(TABLE_NAME, cv, COLUMN_SHOP_ID +"="+ s.getShopId(),null);
    }

    public void delete (Shop s){
        db.delete(TABLE_NAME, COLUMN_SHOP_ID + "=" + s.getShopId(), null);
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }

}
