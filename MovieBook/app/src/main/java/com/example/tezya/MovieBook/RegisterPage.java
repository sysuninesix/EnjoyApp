package com.example.tezya.MovieBook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    EditText UsernameEdit;
    EditText PasswordEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        UsernameEdit= (EditText)findViewById(R.id.UsernameEdit);
        PasswordEdit= (EditText)findViewById(R.id.PasswordEdit);
      final EditText PasswordRepeatEdit= (EditText)findViewById(R.id.PasswordRepeatEdit);
        Button RegisterButton= (Button)findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用户密码不能为空，且密码重复密码要相等
                if (!UsernameEdit.getText().toString().equals("")&& PasswordRepeatEdit.getText().toString().equals(PasswordEdit.getText().toString())&&!PasswordEdit.getText().toString().equals("")){
                    if(dealwithdatabase(UsernameEdit.getText().toString(),PasswordEdit.getText().toString())){
                        Toast.makeText(getApplicationContext(),"用户创建成功",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"用户名重复",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"用户名和密码都不能为空",Toast.LENGTH_LONG).show();
                }
            }
        });




    }
    //sqlite 插入用户数据库
    boolean dealwithdatabase(String username, String password){
        DBHelper dbHelper= new DBHelper(getApplicationContext());
        StringBuffer whereBuffer = new StringBuffer();
        whereBuffer.append(DBHelper.FIELD_Username).append(" = ").append("'").append(username).append("'");
        //指定要查询的是哪几列数据
        String[] columns = {DBHelper.FIELD_Password};
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询数据库
        Cursor cursor = null;
        boolean flag= true;

        cursor = db.query(DBHelper.TABLE_NAME, columns, whereBuffer.toString(), null, null, null, null);
        if (cursor.getCount()!= 0) {
            cursor.close();
            db.close();
            return false;
        }
        db= dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_Username,username);
        cv.put(DBHelper.FIELD_Password,password);
        db.insert(DBHelper.TABLE_NAME,null,cv);
        db.close();
        return true;

    }
}
