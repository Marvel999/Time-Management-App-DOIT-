package com.example.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.doit.ui.NotesFragment;
import com.example.doit.ui.ProfileFragment;
import com.example.doit.ui.TODO_List;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class Main_app extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        BubbleNavigationConstraintView bubbleNavigation=(BubbleNavigationConstraintView)findViewById(R.id.top_navigation_constraint);

        Fragment fragment=new TODO_List();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContener, fragment);
        transaction.commit();

        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                Log.e( "onNavigationChanged: ",""+position );
                if(position==0){
                    Fragment fragment=new TODO_List();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContener, fragment);
                    transaction.commit();                }

                if(position==1){
                    Fragment fragment=new NotesFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContener, fragment);
                    transaction.commit();                }

                if(position==2){
                    Fragment fragment=new ProfileFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContener, fragment);
                    transaction.commit();                }
            }
        });
    }
}
