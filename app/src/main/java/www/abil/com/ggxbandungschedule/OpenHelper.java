package www.abil.com.ggxbandungschedule;

/**
 * Created by LENOVO G40-45 on 11/1/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yudiwbs on 18/10/2016.
 */

public class OpenHelper extends SQLiteOpenHelper {

    //kalau ada upgrage, increment versi database
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dbMhs.db";
    public static final String TABLE_CREATE =
            "CREATE TABLE MAHASISWA (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT, TELEPON TEXT)";

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create database
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //jika app diupgrade (diinstall yang baru) maka database akan dicreate ulang (data hilang)
        //jika tidak tidak ingin hilang, bisa diproses disini
        db.execSQL("DROP TABLE IF EXITS MAHASISWA");
    }

}
