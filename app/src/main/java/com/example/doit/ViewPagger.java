package com.example.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pagger);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        List<Fragment> list=new ArrayList<>();
        list.add(new firstviewpagger());
        list.add(new secondviewpagger());
        list.add(new thirdviewpagger());

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
}
