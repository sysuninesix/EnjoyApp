package com.example.tezya.MovieBook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import static android.R.attr.password;
import static com.example.tezya.MovieBook.MovieDatabase.MovList;
import static com.example.tezya.MovieBook.MovieDatabase.username;

public class BookingPage extends AppCompatActivity {
    int date;
    int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        final int id= bundle.getInt("Id");
        ImageView imageView= (ImageView)findViewById(R.id.MovieImage);
        imageView.setImageResource(MovieDatabase.srcList[id]);
        TextView Title= (TextView)findViewById(R.id.Title);
        Title.setText(MovList[id]);
        TextView Price= (TextView)findViewById(R.id.Price);
        Price.setText(""+MovieDatabase.pricelist[id]+"元");
        final Spinner datesp = (Spinner) findViewById(R.id.Datechoose);
        date= datesp.getSelectedItemPosition();
        datesp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                date= datesp.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final Spinner timesp = (Spinner) findViewById(R.id.Timechoose);
        time= datesp.getSelectedItemPosition();
        datesp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time= datesp.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button button= (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper= new DBHelper(getApplicationContext());
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(DBHelper.FIELD_Username,username);
                cv.put("MovieType",id);
                cv.put("MovieDate",date);
                cv.put("MovieTime",time);
                cv.put("columns","4,5,6");
                cv.put("rows","1,1,1");
                db.insert("UserPage",null,cv);
                db.close();
                Intent intent = new Intent(BookingPage.this,SeatPage.class);
                ActivityCollector.addActivity(BookingPage.this);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_user:
                Intent intent = new Intent(this, UserPage.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
