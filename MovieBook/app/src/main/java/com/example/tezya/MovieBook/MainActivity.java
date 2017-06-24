package com.example.tezya.MovieBook;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {
    EditText UsernameEdit;
    EditText PasswordEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button LoginButton= (Button)findViewById(R.id.LogInButton);
        final Button RegisterButton= (Button)findViewById(R.id.RegisterButton);
        UsernameEdit= (EditText)findViewById(R.id.UsernameEdit);
        PasswordEdit= (EditText)findViewById(R.id.PasswordEdit);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (querywithdatabase(UsernameEdit.getText().toString(),PasswordEdit.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, APPMainActivity.class);
                    Bundle bundle= new Bundle();
                    bundle.putString("username",UsernameEdit.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"用户名或密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterPage.class);
                startActivity(intent);
            }
        });

    }

    boolean querywithdatabase(String username,String password){
        StringBuffer whereBuffer = new StringBuffer();
        whereBuffer.append(DBHelper.FIELD_Username).append(" = ").append("'").append(username).append("'");
        //指定要查询的是哪几列数据
        String[] columns = {DBHelper.FIELD_Password};
        DBHelper dbHelper= new DBHelper(getApplicationContext());
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询数据库
        Cursor cursor = null;
        boolean flag= true;

            cursor = db.query(DBHelper.TABLE_NAME, columns, whereBuffer.toString(), null, null, null, null);
        if (cursor.getCount()== 0)
            flag= false;

        while(cursor.moveToNext()) {
            int count = cursor.getColumnCount();

            String columName = cursor.getColumnName(0);
            Log.e("tag", columName);
            String tname = cursor.getString(0);
            Log.e("tag", "count = " + count + " columName = " + columName + "  name =  " + tname);
            cursor.moveToFirst();
            if (!tname.equals(password))
                flag = false;
        }



            if (cursor != null) {
                cursor.close();
            }

        //关闭数据库
        db.close();
        return flag;
    }

}
