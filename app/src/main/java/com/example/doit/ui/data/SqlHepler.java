package com.example.doit.ui.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlHepler extends SQLiteOpenHelper {

    public static final String dbname="DOIT";
    public  static final int version=1;
    public SqlHepler(Context context){
        super(context,dbname,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String sql="CREATE TABLE TASKLIST (_id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT,SORTDESCRIPTION TEXT,DESCRIPTION TEXT,TIME TEXT,DATE TEXT,CATEGORY TEXT,ISDONE INTEGER)";
      db.execSQL(sql);
        String sql1="CREATE TABLE AUTH (_id INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT,EMAILID TEXT,MOBILE TEXT,COLLEGE TEXT,PASSWORD TEXT)";
       db.execSQL(sql1);

       insertdata(db,"Manish","Student","I a programmer","2:00","27-09-2020","Home",0);
       insertAuth(db,"Manish","manishjobs1999@gmail.com","9990147259","NIEC","Manish@1999");
    }

    public void insertdata(SQLiteDatabase sqldb,String title,String shortDescription, String description,String time,String date,String catagery,int isDone){
        ContentValues values=new ContentValues();
        values.put("TITLE",title);
        values.put("SORTDESCRIPTION",shortDescription);
        values.put("DESCRIPTION",description);
        values.put("TIME",time);
        values.put("DATE",date);
        values.put("CATEGORY",catagery);
        values.put("ISDONE",isDone);

        sqldb.insert("TASKLIST",null,values);

    }


    public void insertAuth(SQLiteDatabase sqldb,String username,String emailid, String mobile,String college,String password){
        ContentValues values=new ContentValues();
        values.put("USERNAME",username);
        values.put("EMAILID",emailid);
        values.put("MOBILE",mobile);
        values.put("COLLEGE",college);
        values.put("PASSWORD",password);

        sqldb.insert("AUTH",null,values);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
