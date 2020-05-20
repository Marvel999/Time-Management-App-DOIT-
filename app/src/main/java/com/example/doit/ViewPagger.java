package com.example.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.doit.ui.data.SqlHepler;
import com.example.doit.viewpagger.firstviewpagger;
import com.example.doit.viewpagger.secondviewpagger;
import com.example.doit.viewpagger.thirdviewpagger;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class ViewPagger extends AppCompatActivity {
    ViewPager viewpager;
    SlideViewpaggerAdopter slideViewpaggerAdopter;
    Button getstarted;
    SqlHepler sqlHepler;
    SQLiteDatabase sqLiteDatabase,sqLiteDatabase1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pagger);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        sqlHepler=new SqlHepler(this);
        sqLiteDatabase=sqlHepler.getReadableDatabase();
        sqLiteDatabase1=sqlHepler.getWritableDatabase();

        List<Fragment> list=new ArrayList<>();
        list.add(new firstviewpagger());
        list.add(new secondviewpagger());
        list.add(new thirdviewpagger());
        setdata();

        getstarted=(Button)findViewById(R.id.singupbutton);


        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewPagger.this,Login_Singup.class);
                startActivity(intent);
                finish();
            }
        });

        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);


        viewpager= findViewById(R.id.viewpager);
        slideViewpaggerAdopter=new SlideViewpaggerAdopter(getSupportFragmentManager(),list);
        viewpager.setAdapter(slideViewpaggerAdopter);
        wormDotsIndicator.setViewPager(viewpager);



        int i=slideViewpaggerAdopter.getCount();
        Log.e( "count value",""+i );




    }

    public void setdata(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT TITLE,ISDONE,DESCRIPTION FROM TASKLIST",new String[]{});

        if(cursor != null){
            cursor.moveToFirst();
        }
        StringBuilder s=new StringBuilder();
        do{
            String name=cursor.getString(2);
            s.append("TITLE- "+name+"\n");
        }
        while (cursor.moveToNext());

        Log.e("DATABASE",""+s);

        }




//    public void update(String name){
//        ContentValues values=new ContentValues();
//        values.put("NAME",name);
//        Log.e( "update: ","Yes" );
//        sqLiteDatabase.update("PRODUCTS",values,"_id=?",new String[]{"1"});
//        /**
//         *                               OR
//         *              sqLiteDatabase.update("PRODUCTS",values,"NAME=? AND PRICE=?",new String[]{"Jam","3233.34"});
//         */
//    }



}
