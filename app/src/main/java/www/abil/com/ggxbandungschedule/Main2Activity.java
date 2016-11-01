package www.abil.com.ggxbandungschedule;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Main2Activity extends AppCompatActivity {
    //menggunakan arrayList untuk menyimpan
    //data yang akan ditampilkan
    private ArrayList<String> items2 = new ArrayList<>();

    //penghubung antara data dengan listview
    ArrayAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //inisiasi isi
        items2.add("Jadwal 1");
        items2.add("Jadwal 2");

        ListView listManage = (ListView) findViewById(R.id.listManage);


       /*buat adapter
       3 parameter:
          - context:
          - layout listview: disini kita menggunakan yg sudah ada (nantinya bisa custom)
          - datanya: items
       */
        adapter2 = new ArrayAdapter (this, android.R.layout.simple_expandable_list_item_1, items2);

        //set adapter ke listview
        listManage.setAdapter(adapter2);

}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // cek request code
        if (requestCode == ACT3_REQUEST) {
            String workout = data.getStringExtra(WORKOUT);
            String instructor = data.getStringExtra(INSTRUCTOR);
            String day = data.getStringExtra(DAY);
            String time = data.getStringExtra(TIME);
            String place = data.getStringExtra(PLACE);
            String cover = data.getStringExtra(COVER);

            //insert schedule
            DbSchedule db = new DbSchedule(getApplicationContext());
            db.open();
            db.insertSchedule(workout, instructor, day, time, place, cover);

            items2.add(workout + " dengan instruktur " + instructor + ". Hari " +day+" pukul "+time+ ".");
            final ListView listSchedule = (ListView) findViewById(R.id.listManage);
            //biar bisa diklik
            listSchedule.setClickable(true);
            listSchedule.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    String isiBaris = (String) listSchedule.getItemAtPosition(position);
                    String pesan = "Kelas Cover";
                    Toast toast = Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            //JANGAN LUPA refresh listview, agar layar terupdate
            adapter2.notifyDataSetChanged();


        }
    }

    public final static String WORKOUT = "com.abil.www.ggxbandungschedule.WORKOUT";
    public final static String INSTRUCTOR = "com.abil.www.ggxbandungschedule.INSTRUCTOR";
    public final static String DAY = "com.abil.www.ggxbandungschedule.DAY";
    public final static String TIME = "com.abil.www.ggxbandungschedule.TIME";
    public final static String PLACE = "com.abil.www.ggxbandungschedule.PLACE";
    public final static String COVER = "com.abil.www.ggxbandungschedule.COVER";
    static final int ACT3_REQUEST = 98;  // request code

    public void klikAdd(View v) {
        Intent intent3 = new Intent(this, Main3Activity.class);
        startActivityForResult(intent3,ACT3_REQUEST);
    }

}
