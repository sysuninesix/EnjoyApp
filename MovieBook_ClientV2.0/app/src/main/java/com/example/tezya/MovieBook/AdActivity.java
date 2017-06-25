package com.example.tezya.MovieBook;

import java.util.TimerTask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class AdActivity extends AppCompatActivity {

    TextView countdown;
    public int time = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        RelativeLayout relativeLayout= (RelativeLayout)findViewById(R.id.activity_ad);
        int a=(int) (Math.random()*6);
        relativeLayout.setBackgroundResource(MovieDatabase.srcList[a]);
        countdown = (TextView)findViewById(R.id.countdown);
        handler.postDelayed(runnable, 1000);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            try {
                handler.postDelayed(this, 1000);
                countdown.setText(Integer.toString(time--));
                if(time == 0) {
                    Intent intent = new Intent(AdActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("exception...");
            }
        }
    };

}
