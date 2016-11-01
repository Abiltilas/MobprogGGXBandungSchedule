package www.abil.com.ggxbandungschedule;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

/**
 * Created by LENOVO G40-45 on 11/1/2016.
 */

public class DbSchedule {

    //class untuk menyimpan record
    public static class Schedule {
        public String workout;
        public String instructor;
        public String day;
        public String time;
        public String place;
        public String cover;
    }


    private SQLiteDatabase db;
    private final OpenHelper dbHelper;

    public DbSchedule(Context c) {
        dbHelper =  new OpenHelper(c);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long insertSchedule(String workout, String instructor, String day, String time, String place, String cover) {
        ContentValues newValues = new ContentValues();
        newValues.put("WORKOUT", workout);
        newValues.put("INSTRUCTOR", instructor);
        newValues.put("DAY", day);
        newValues.put("TIME", time);
        newValues.put("PLACE", place);
        newValues.put("COVER", cover);
        return db.insert("SCHEDULE", null, newValues);
    }

    //ambil data schedule berdasarkan workout
    public Schedule getSchedule(String workout) {
        Cursor cur = null;
        Schedule M = new Schedule();

        //kolom yang diambil
        String[] cols = new String [] {"ID", "WORKOUT", "INSTRUCTOR", "DAY", "TIME", "PLACE", "COVER"};
        //parameter, akan mengganti ? pada WORKOUT=?
        String[] param  = {workout};

        cur = db.query("SCHEDULE",cols,"WORKOUT=?",param,null,null,null);

        if (cur.getCount()>0) {  //ada data? ambil
            cur.moveToFirst();
            M.workout = cur.getString(1);
            M.instructor = cur.getString(2);
            M.day = cur.getString(3);
            M.time = cur.getString(4);
            M.place = cur.getString(5);
            M.cover = cur.getString(6);
        }

        return M;
    }

}

