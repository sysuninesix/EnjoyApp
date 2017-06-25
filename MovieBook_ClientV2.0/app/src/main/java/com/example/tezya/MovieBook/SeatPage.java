package com.example.tezya.MovieBook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tezya.MovieBook.SeatTable;

import java.util.ArrayList;

import static com.example.tezya.MovieBook.MovieDatabase.username;

public class SeatPage extends AppCompatActivity {
    public SeatTable seatTableView;
    int i= 0;
    String[] seat;
    private void init(final int id,final int count) {
        if (NetWorkUtil.isNetWorkOpened(SeatPage.this)) {
            Log.e("haha", "linked");
            new AsyncTask<String, Integer, Response>() {
                @Override
                protected Response doInBackground(String... params) {
                    Response response = WebService.check(id, count);
                    return response;
                }

                @Override
                protected void onPostExecute(Response response) {
                    if (response == null) {
                        Toast.makeText(SeatPage.this, "check failed,response is null",
                                Toast.LENGTH_SHORT).show();
                        recreate();
                    } else if (200 == response.getStatus()) {
                        Log.e("warning", "user======" + response.toString());
                        Object obj = response.getResponse();
                        if (obj == null) {
                            Toast.makeText(SeatPage.this,
                                    "check failed,the response field is null",
                                    Toast.LENGTH_SHORT).show();
                            recreate();

                        } else {
                            Toast.makeText(SeatPage.this, "check succeed",
                                    Toast.LENGTH_SHORT).show();
                            Log.e("haha", String.valueOf(i));
                            String temp = response.getMessage();
                            seat= temp.split(",");
                            Log.e("haha",temp);

                            seatTableView = (SeatTable) findViewById(R.id.seatView);
                            seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
                            seatTableView.setMaxSelected(1);
                            seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

                                @Override
                                public boolean isValidSeat(int row, int column) {
                                    if(column==15) {
                                        return false;
                                    }
                                    return true;
                                }

                                @Override
                                public boolean isSold(int row, int column) {
                                    for (int i= 0;i< seat.length;i++){
                                        Log.e("haha",String.valueOf(i));
                                        if (row+1== Integer.parseInt(seat[i])) {
                                            if(column+1== Integer.parseInt(seat[++i]))
                                                return true;
                                        }
                                        else
                                            i++;
                                    }
                                    return false;
                                }

                                @Override
                                public void checked(int row, int column) {

                                }

                                @Override
                                public void unCheck(int row, int column) {

                                }

                                @Override
                                public String[] checkedSeatTxt(int row, int column) {
                                    return null;
                                }

                            });
                            seatTableView.setData(10,14);



                        }
                    } else {
                        Toast.makeText(SeatPage.this,
                                "book failed，" + response.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        recreate();
                    }
                    super.onPostExecute(response);
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seat_page);
        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        final int count= bundle.getInt("count");
        final int id= bundle.getInt("id");
        init(id,count);
        //设置最多选中




        Button BuyButton= (Button)findViewById(R.id.BuyButton);
        BuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  ArrayList<Integer> arrayList=seatTableView.getselectedSeat();
                new AlertDialog.Builder(SeatPage.this).setTitle("系统提示")//设置对话框标题

                        .setMessage("是否确定购票")//设置显示的内容

                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮



                            @Override

                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                // TODO Auto-generated method stub
                                for ( i= 0; i< arrayList.size();i++) {
                                    final int row = arrayList.get(i);
                                    final int column = arrayList.get(++i);

                                    if (NetWorkUtil.isNetWorkOpened(SeatPage.this)) {
                                        Log.e("haha", "linked");
                                        new AsyncTask<String, Integer, Response>() {
                                            @Override
                                            protected Response doInBackground(String... params) {
                                                Response response = WebService.book(id, count + row + 1, column + 1);
                                                return response;
                                            }

                                            @Override
                                            protected void onPostExecute(Response response) {
                                                if (response == null) {
                                                    Toast.makeText(SeatPage.this, "book failed,response is null",
                                                            Toast.LENGTH_SHORT).show();
                                                    recreate();
                                                } else if (200 == response.getStatus()) {
                                                    Log.e("warning", "user======" + response.toString());
                                                    Object obj = response.getResponse();
                                                    if (obj == null) {
                                                        Toast.makeText(SeatPage.this,
                                                                "book failed,the response field is null",
                                                                Toast.LENGTH_SHORT).show();
                                                        recreate();

                                                    } else {
                                                        Toast.makeText(SeatPage.this, "book succeed",
                                                                Toast.LENGTH_SHORT).show();
                                                        Log.e("haha",String.valueOf(i));
                                                        DBHelper dbHelper= new DBHelper(getApplicationContext());
                                                        SQLiteDatabase db= dbHelper.getWritableDatabase();
                                                        ContentValues cv = new ContentValues();
                                                        cv.put(DBHelper.FIELD_Username,username);
                                                        cv.put("MovieType",id-1);
                                                        int time= count/10%4;
                                                        int date= (count/10-time)/4;
                                                        cv.put("MovieDate",date + 1);
                                                        cv.put("MovieTime",time);
                                                        cv.put("columns",arrayList.get(1)+1);
                                                        cv.put("rows",arrayList.get(0)+1);
                                                        db.insert("UserPage",null,cv);
                                                        db.close();
                                                            Intent intent = new Intent(SeatPage.this, Success.class);
                                                            ActivityCollector.addActivity(SeatPage.this);
                                                            startActivity(intent);
                                                            finish();





                                                    }
                                                } else {
                                                    Toast.makeText(SeatPage.this,
                                                            "book failed，" + response.getMessage(),
                                                            Toast.LENGTH_SHORT).show();
                                                    recreate();
                                                }
                                                super.onPostExecute(response);
                                            }
                                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


                                    }
                                }


                            }

                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮



                    @Override

                    public void onClick(DialogInterface dialog, int which) {//响应事件

                        // TODO Auto-generated method stub



                    }

                }).show();//在按键响应事件中显示此对话框

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

