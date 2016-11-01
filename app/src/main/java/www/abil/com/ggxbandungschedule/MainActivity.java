package www.abil.com.ggxbandungschedule;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //menggunakan arrayList untuk menyimpan
    //data yang akan ditampilkan
    private ArrayList<String> items = new ArrayList<>();

    //penghubung antara data dengan listview
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Buat toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        //inisiasi isi
        items.add("Monday");
        items.add("Tuesday");
        items.add("Wednesday");
        items.add("Thursday");
        items.add("Friday");
        items.add("Saturday");
        items.add("Sunday");

        final ListView listSchedule = (ListView) findViewById(R.id.listSchedule);
        //biar bisa diklik
        listSchedule.setClickable(true);
        listSchedule.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String isiBaris = (String) listSchedule.getItemAtPosition(position);
                String pesan = "Day : "+ isiBaris;
                Toast toast = Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

       /*buat adapter
       3 parameter:
          - context:
          - layout listview: disini kita menggunakan yg sudah ada (nantinya bisa custom)
          - datanya: items
       */
        adapter = new ArrayAdapter (this, android.R.layout.simple_expandable_list_item_1, items);


        //set adapter ke listview

        listSchedule.setAdapter(adapter);
    }

    //mengambil data dari activity lain
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // cek request code
        if (requestCode == ACT2_REQUEST) {
            String pesan = data.getStringExtra(ADD);
            //masukkan data add dari activity 2
            String add = data.getStringExtra(ADD);
            items.add(add);
            //Refresh listview, agar layar terupdate
            adapter.notifyDataSetChanged();
            //Tampilkan toast data berhasil ditambah
            Toast toast = Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    //bikin menu ada di toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    //Menu action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mManage:
                Intent intent2 = new Intent(this, Main2Activity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //konstanta, supaya bisa membedakan antar message
    public final static String ADD = "com.abil.www.ggxbandungschedule.ADD";
    static final int ACT2_REQUEST = 99;  // request code




}



