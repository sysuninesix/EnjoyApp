package com.example.tezya.MovieBook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserPage extends AppCompatActivity {



    private ListView lv;
    private List<MovieInfo> MovieList=  new ArrayList<MovieInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        TextView usernametext= (TextView)findViewById(R.id.UsernameText);
        usernametext.setText(MovieDatabase.username);
        querywithdatabase(MovieDatabase.username);
        lv = (ListView) findViewById(R.id.listview1);
        lv.setAdapter( new BaseAdapter(){
            //返回多少条记录
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return MovieList.size();
            }
            //每一个item项，返回一次界面
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = null;
                //布局不变，数据变

                //如果缓存为空，我们生成新的布局作为1个item
                if(convertView==null){
                    Log.i("info:", "没有缓存，重新生成"+position);
                    LayoutInflater inflater = UserPage.this.getLayoutInflater();
                    //因为getView()返回的对象，adapter会自动赋给ListView
                    view = inflater.inflate(R.layout.useritem, null);
                }else{
                    Log.i("info:", "有缓存，不需要重新生成"+position);
                    view = convertView;
                }
                //布局不变，数据变


                MovieInfo m = MovieList.get(position);



                ImageView moviesrc = (ImageView)view.findViewById(R.id.MovieImage);
                moviesrc.setImageResource(  MovieDatabase.srcList[m.getMovieId()]  );


                TextView moviename = (TextView)view.findViewById(R.id.MovieName);
               moviename.setText( MovieDatabase.MovList[m.getMovieId()]  );
                TextView moviedate = (TextView)view.findViewById(R.id.Date);
                moviedate.setText( "6-"+m.getMovieTime());
                TextView movietime = (TextView)view.findViewById(R.id.time);
                movietime.setText( MovieDatabase.timelist[m.getMovieTime()]  );
                TextView movieInfo= (TextView)view.findViewById(R.id.info);
                String []coltemp= m.getColumns().split(",");
                String []rowtemp= m.getRows().split(",");
                String temp="";
                for (int i= 0;i< coltemp.length;i++){
                    temp+= ","+rowtemp[i]+"排"+coltemp[i]+"列";
                }
                movieInfo.setText(temp);

                return view;
            }

            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return MovieList.get(position);
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return position;
            }

        } );
    }
    void querywithdatabase(String username){
        StringBuffer whereBuffer = new StringBuffer();
        whereBuffer.append(DBHelper.FIELD_Username).append(" = ").append("'").append(username).append("'");
        //指定要查询的是哪几列数据
        String[] columns = {"MovieType","MovieDate","MovieTime","columns","rows"};
        DBHelper dbHelper= new DBHelper(getApplicationContext());
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询数据库
        Cursor cursor = null;


        cursor = db.query("UserPage", columns, whereBuffer.toString(), null, null, null, null);


        while(cursor.moveToNext()) {
            int movietype = cursor.getInt(0);
            int moviedate= cursor.getInt(1);
            int movietime= cursor.getInt(2);
            String column= cursor.getString(3);
            String rows= cursor.getString(4);
            MovieInfo m= new MovieInfo(movietype,moviedate,column,rows,movietime);
            MovieList.add(m);



        }



        if (cursor != null) {
            cursor.close();
        }

        //关闭数据库
        db.close();

    }


}
