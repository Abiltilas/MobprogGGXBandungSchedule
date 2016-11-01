package www.abil.com.ggxbandungschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //ambil intent
        Intent intent3 = getIntent();
        //ambil datanya
        String ambilworkout = intent3.getStringExtra(Main2Activity.WORKOUT);
        String ambilinstructor = intent3.getStringExtra(Main2Activity.INSTRUCTOR);
        String ambilday = intent3.getStringExtra(Main2Activity.DAY);
        String ambiltime = intent3.getStringExtra(Main2Activity.TIME);
        String ambilplace = intent3.getStringExtra(Main2Activity.PLACE);
        String ambilcover = intent3.getStringExtra(Main2Activity.COVER);

    }

    public void klikDone(View v) {
        //Ambil data add
        EditText etWorkout = (EditText) findViewById(R.id.etWorkout);
        EditText etInstructor = (EditText) findViewById(R.id.etInstructor);
        EditText etDay = (EditText) findViewById(R.id.etDay);
        EditText etTime = (EditText) findViewById(R.id.etTime);
        EditText etPlace = (EditText) findViewById(R.id.etPlace);
        EditText etCover = (EditText) findViewById(R.id.etCover);
        String workout = etWorkout.getText().toString();
        String instructor = etInstructor.getText().toString();
        String day = etDay.getText().toString();
        String time = etTime.getText().toString();
        String place = etPlace.getText().toString();
        String cover = etCover.getText().toString();

        Intent intent3 = getIntent();
        intent3.putExtra(Main2Activity.WORKOUT, workout);
        intent3.putExtra(Main2Activity.INSTRUCTOR, instructor);
        intent3.putExtra(Main2Activity.DAY, day);
        intent3.putExtra(Main2Activity.TIME, time);
        intent3.putExtra(Main2Activity.PLACE, place);
        intent3.putExtra(Main2Activity.COVER, cover);
        setResult(RESULT_OK,intent3);
        finish();
    }

}
