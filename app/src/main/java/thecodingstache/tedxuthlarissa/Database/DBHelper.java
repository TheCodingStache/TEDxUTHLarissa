package thecodingstache.tedxuthlarissa.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.IOException;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "barcode";
    private static final String DB_NAME = "barcode.db";
    private static final String COL_ID = "id";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table barcode (id INTEGER PRIMARY KEY AUTOINCREMENT, img BLOB NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertImage(String x, Integer i) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            FileInputStream fs = new FileInputStream(x);
            byte[] imageByte = new byte[fs.available()];
            fs.read(imageByte);
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", i);
            contentValues.put("img", imageByte);
            db.insert("barcode", null, contentValues);
            fs.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Bitmap getImage(Integer id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Bitmap bitmap = null;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from barcode where id =?", new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            byte[] image = cursor.getBlob(1);
            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        }
        return bitmap;
    }

//    public boolean insertData(String code) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_CODE, code);
//        SQLiteDatabase db = this.getWritableDatabase();
//        long results = db.insert(TABLE_NAME, null, contentValues);
//        if (results == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }

//    public ArrayList<DatabaseItem> getAllInformation() {
//        ArrayList<DatabaseItem> arrayList = new ArrayList<>();
//        SQLiteDatabase database = this.getReadableDatabase();
//        Cursor cursor = database.rawQuery("Select * from " + TABLE_NAME, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                int id = cursor.getInt(0);
//                String code = cursor.getString(1);
//                DatabaseItem listItem = new DatabaseItem(id, code);
//                arrayList.add(listItem);
//            }
//        }
//        cursor.close();
//        return arrayList;
//    }


    public void deleteRow(int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = " + value + "");
    }
}
