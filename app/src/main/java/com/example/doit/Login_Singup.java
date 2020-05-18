package com.example.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.doit.ui.SignupFragment;

public class Login_Singup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__singup);

        Fragment fragment=new SignupFragment();
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContenersignup,fragment);
        ft.commit();
    }
}
