package com.example.tezya.MovieBook;

/**
 * Created by xietengxiao on 2017/6/24.
 */

import java.io.Serializable;

/**
 * Created by 123 on 2016/4/10.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -1044671445753823751L;
    private String name;
    private String passwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }



    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}