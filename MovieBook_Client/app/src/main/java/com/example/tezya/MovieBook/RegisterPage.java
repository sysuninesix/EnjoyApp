package com.example.tezya.MovieBook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        UsernameEdit = (EditText) findViewById(R.id.UsernameEdit);
        PasswordEdit = (EditText) findViewById(R.id.PasswordEdit);
        final EditText PasswordRepeatEdit = (EditText) findViewById(R.id.PasswordRepeatEdit);
        Button RegisterButton = (Button) findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用户密码不能为空，且密码重复密码要相等
                final String username = UsernameEdit.getText().toString();
                final String password = PasswordEdit.getText().toString();
                if (!UsernameEdit.getText().toString().equals("") && PasswordRepeatEdit.getText().toString().equals(PasswordEdit.getText().toString()) && !PasswordEdit.getText().toString().equals("")) {
                    if (NetWorkUtil.isNetWorkOpened(RegisterPage.this)) {
                        Log.e("haha", "linked");

                        new AsyncTask<String, Integer, Response>() {
                            @Override
                            protected Response doInBackground(String... params) {
                                Response response = WebService.register(username, password);
                                return response;
                            }

                            @Override
                            protected void onPostExecute(Response response) {
                                if (response == null) {
                                    Toast.makeText(RegisterPage.this, "register failed,response is null",
                                            Toast.LENGTH_SHORT).show();
                                } else if (200 == response.getStatus()) {
                                    Log.e("warning", "user======" + response.toString());
                                    Object obj = response.getResponse();
                                    if (obj == null) {
                                        Toast.makeText(RegisterPage.this,
                                                "register failed,the response field is null",
                                                Toast.LENGTH_SHORT).show();
                                    } else {


                                        Toast.makeText(RegisterPage.this, "register succeed",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("username", UsernameEdit.getText().toString());
                                        intent.putExtras(bundle);
                                        startActivity(intent);


                                    }
                                } else {
                                    Toast.makeText(RegisterPage.this,
                                            "register failed，" + response.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                                super.onPostExecute(response);
                            }


                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        Toast.makeText(getApplicationContext(), "用户名重复", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "用户名和密码都不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
