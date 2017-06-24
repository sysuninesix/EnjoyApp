package com.example.tezya.MovieBook;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.view.VelocityTracker;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.MenuInflater;

public class APPMainActivity extends AppCompatActivity {

    private ListView lv;
    private List<Movie> MovieList=  new ArrayList<Movie>();
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.luguo_big,
            R.mipmap.lilei_big,
            R.mipmap.pirateinfo,
            R.mipmap.nvxia_big,
            R.mipmap.chichi_big
    };
    //存放图片的标题
    private String[]  titles = new String[]{
            "DJ的都市情感故事",
            "张子枫领衔出演校园纯爱故事",
            "船长能否再创奇迹",
            "神奇女侠拯救世界",
            "平行世界的你我"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("按下了back键 onBackPressed()");
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            new AlertDialog.Builder(APPMainActivity.this).setTitle("系统提示")//设置对话框标题

                    .setMessage("是否要推出登陆")//设置显示的内容

                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮



                        @Override

                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                            // TODO Auto-generated method stub

                            finish();

                        }

                    }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮



                @Override

                public void onClick(DialogInterface dialog, int which) {//响应事件

                    // TODO Auto-generated method stub



                }

            }).show();//在按键响应事件中显示此对话框
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appmain);
        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        if (MovieDatabase.username== "")
        MovieDatabase.username= bundle.getString("username");
        mViewPaper = (ViewPager) findViewById(R.id.vp);

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }

        title = (TextView) findViewById(R.id.titleforpicture);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        for (int i = 0; i < 6; i++) {

            //添加数据
            Movie m = new Movie();
            m.setImageSrc(MovieDatabase.srcList[i]);
            m.setMovieName(MovieDatabase.MovList[i]);
            m.setPrice(MovieDatabase.pricelist[i]);
            m.setRate(MovieDatabase.ratelist[i]);
            m.setId(i);
            MovieList.add(m);//上周

        }
        lv = (ListView) findViewById(R.id.listview);
        final MyAdapter myAdapter= new MyAdapter();
        lv.setAdapter( myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(APPMainActivity.this, InfoPage.class);
                View v;
                ViewGroup V= (ViewGroup)view;
                v= V.getChildAt(0);
                V= (ViewGroup)v;
                v= V.getChildAt(1);
                V= (ViewGroup)v;
                v= V.getChildAt(0);
                V= (ViewGroup)v;
                TextView t=(TextView)V.getChildAt(0);
                int id= 0;
                for (;id< 6;id++){
                    if (MovieDatabase.MovList[id].equals(t.getText().toString()))
                        break;
                }

                Bundle bundle= new Bundle();
                bundle.putInt("Id",id);
                intent.putExtras(bundle);
                ActivityCollector.addActivity(APPMainActivity.this);
                startActivity(intent);
            }
        });
       final Spinner sort= (Spinner) findViewById(R.id.SpinnerForSort);
        sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i== 0)
                    Collections.sort(MovieList, new CompareRator());
                else
                    Collections.sort(MovieList, new ComparePrice());
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Spinner stylespinner= (Spinner) findViewById(R.id.SpinnerForStyles);
       stylespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if (i == 0){
                   MovieList.clear();
               MovieList=  new ArrayList<Movie>();
                   for (int x = 0; x < 6; x++) {

                       //添加数据
                       Movie m = new Movie();
                       m.setImageSrc(MovieDatabase.srcList[x]);
                       m.setMovieName(MovieDatabase.MovList[x]);
                       m.setPrice(MovieDatabase.pricelist[x]);
                       m.setRate(MovieDatabase.ratelist[x]);
                       m.setId(x);
                       MovieList.add(m);//上周

                   }
               }
               else if (i== 1){
                   MovieList.clear();
                   MovieList=  new ArrayList<Movie>();
                   for (int x = 0; x < 6; x++) {
                       if (MovieDatabase.romantic[x]== false)
                           continue;
                       //添加数据
                       Movie m = new Movie();
                       m.setImageSrc(MovieDatabase.srcList[x]);
                       m.setMovieName(MovieDatabase.MovList[x]);
                       m.setPrice(MovieDatabase.pricelist[x]);
                       m.setRate(MovieDatabase.ratelist[x]);
                       m.setId(x);
                       MovieList.add(m);//上周

                   }
               }
               else if (i== 2){
                   MovieList.clear();
                   MovieList=  new ArrayList<Movie>();
                   for (int x = 0; x < 6; x++) {
                       if (MovieDatabase.comedy[x]== false)
                           continue;
                       //添加数据
                       Movie m = new Movie();
                       m.setImageSrc(MovieDatabase.srcList[x]);
                       m.setMovieName(MovieDatabase.MovList[x]);
                       m.setPrice(MovieDatabase.pricelist[x]);
                       m.setRate(MovieDatabase.ratelist[x]);
                       m.setId(x);
                       MovieList.add(m);//上周

                   }
               }
               else if (i== 3){
                   MovieList.clear();
                   MovieList=  new ArrayList<Movie>();
                   for (int x = 0; x < 6; x++) {
                       if (MovieDatabase.action[x]== false)
                           continue;
                       //添加数据
                       Movie m = new Movie();
                       m.setImageSrc(MovieDatabase.srcList[x]);
                       m.setMovieName(MovieDatabase.MovList[x]);
                       m.setPrice(MovieDatabase.pricelist[x]);
                       m.setRate(MovieDatabase.ratelist[x]);
                       m.setId(x);
                       MovieList.add(m);//上周

                   }
               }
               else if (i== 4){
                   MovieList.clear();
                   MovieList=  new ArrayList<Movie>();
                   for (int x = 0; x < 6; x++) {
                       if (MovieDatabase.horror[x]== false)
                           continue;
                       //添加数据
                       Movie m = new Movie();
                       m.setImageSrc(MovieDatabase.srcList[x]);
                       m.setMovieName(MovieDatabase.MovList[x]);
                       m.setPrice(MovieDatabase.pricelist[x]);
                       m.setRate(MovieDatabase.ratelist[x]);
                       m.setId(x);
                       MovieList.add(m);//上周

                   }
               }
               int temp= sort.getSelectedItemPosition();
               if(temp== 0)
                   Collections.sort(MovieList, new CompareRator());
               else
                   Collections.sort(MovieList, new ComparePrice());

               myAdapter.notifyDataSetChanged();
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
    }

    public class MyAdapter extends BaseAdapter{
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
                LayoutInflater inflater = APPMainActivity.this.getLayoutInflater();
                //因为getView()返回的对象，adapter会自动赋给ListView
                view = inflater.inflate(R.layout.item, null);
            }else{
                Log.i("info:", "有缓存，不需要重新生成"+position);
                view = convertView;
            }
            //布局不变，数据变


            Movie m = MovieList.get(position);
            TextView movieName = (TextView)view.findViewById(R.id.MovieName);
            movieName.setText(  m.getMovieName()  );


            ImageView moviesrc = (ImageView)view.findViewById(R.id.MovieImage);
            moviesrc.setImageResource(  m.getImageSrc()  );


            TextView price = (TextView)view.findViewById(R.id.Price);
            price.setText( ""+m.getPrice() +"元" );
            RatingBar ratingBar= (RatingBar)view.findViewById(R.id.Rate);
            ratingBar.setRating((float)m.getRate());
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

    }
    public static final class CompareRator implements Comparator<Movie> {

        @Override
        public int compare(Movie object1, Movie object2) {
            double m1 = object1.getRate();
            double m2 = object2.getRate();
            int result = 0;
            if (m1 < m2) {
                result = 1;
            }
            if (m1 > m2) {
                result = -1;
            }
            return result;
        }
    }
    public static final class ComparePrice implements Comparator<Movie> {

        @Override
        public int compare(Movie object1, Movie object2) {
            double m1 = object1.getPrice();
            double m2 = object2.getPrice();
            int result = 0;
            if (m1 < m2) {
                result = 1;
            }
            if (m1 > m2) {
                result = -1;
            }
            return result;
        }
    }



    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }


    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private android.os.Handler mHandler = new android.os.Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
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
