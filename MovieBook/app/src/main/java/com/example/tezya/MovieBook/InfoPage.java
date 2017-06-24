package com.example.tezya.MovieBook;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import static com.example.tezya.MovieBook.MovieDatabase.MovList;
import static com.example.tezya.MovieBook.MovieDatabase.actorlist;
import static com.example.tezya.MovieBook.MovieDatabase.detailslist;
import static com.example.tezya.MovieBook.MovieDatabase.ratelist;
import static com.example.tezya.MovieBook.MovieDatabase.srcInfoList;
import static com.example.tezya.MovieBook.MovieDatabase.srcList;

public class InfoPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        final int id= bundle.getInt("Id");
        TextView text1= (TextView)findViewById(R.id.text1);
        text1.setText(MovieDatabase.actorlist[id][0]);
        TextView text2= (TextView)findViewById(R.id.text2);
        text2.setText(MovieDatabase.actorlist[id][1]);
        TextView text3= (TextView)findViewById(R.id.text3);
        text3.setText(MovieDatabase.actorlist[id][2]);
        ImageView image1= (ImageView)findViewById(R.id.image1);
        image1.setImageResource(MovieDatabase.picture[id][0]);
        ImageView image2= (ImageView)findViewById(R.id.image2);
        image2.setImageResource(MovieDatabase.picture[id][1]);
        ImageView image3= (ImageView)findViewById(R.id.image3);
        image3.setImageResource(MovieDatabase.picture[id][2]);
        TextView Title= (TextView)findViewById(R.id.Title);
        Title.setText(MovList[id]);
        ImageView imageView= (ImageView)findViewById(R.id.MovieImage);
        imageView.setImageResource(srcInfoList[id]);
        RatingBar ratingBar= (RatingBar)findViewById(R.id.Rate);
        ratingBar.setRating((float)ratelist[id]);
        TextView info2text= (TextView)findViewById(R.id.Info2);
        info2text.setText(detailslist[id]);
        Button button= (Button)findViewById(R.id.ToBookingPage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoPage.this, BookingPage.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id",id);
                intent.putExtras(bundle);
                ActivityCollector.addActivity(InfoPage.this);
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
