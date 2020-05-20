package com.example.doit.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.doit.Main_app;
import com.example.doit.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;


public class SignupFragment extends Fragment {
     TextInputLayout username;
     TextInputLayout email;
     TextInputLayout mobile;
    TextInputLayout password;

    TextInputLayout college;
     Button singup;
     TextView signuptext;
    public static final Pattern MOBILE_NUMBER
            = Pattern.compile("^"+
            "(?=.*[0-9])"+
            "(?=\\S+$)"+
            "(.{10,})"+
            "$");


    public static final Pattern Password_Pattern
            = Pattern.compile("^"+
            "(?=.*[0-9])"+
            "(?=.*[a-z])"+
            "(?=.*[A-Z])"+
            "(?=.*[!@#$%^&*?/*-])"+
            "(?=\\S+$)"+
            "(.{6,})"+
            "$");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_signup, container, false);
        Button signupbtn=root.findViewById(R.id.signup_btn);
         username =root.findViewById(R.id.input_username);
         email=root.findViewById(R.id.input_emailid);
         mobile=root.findViewById(R.id.input_Mobileno);
         college=root.findViewById(R.id.input_college);
         password=root.findViewById(R.id.input_password);
        signuptext=root.findViewById(R.id.signup);

        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new LoginFragment();
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContenersignup,fragment);
                ft.disallowAddToBackStack();
                ft.commit();
            }
        });



        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!confomCollege() | !confomEmail() | !confomMobile() | !confomPassword() | !confomUsername()){
                    return;
                }
                Fragment fragment=new LoginFragment();
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContenersignup,fragment);
                ft.commit();


            }
        });

        return root;
    }


    public boolean confomPassword(){
        String inputPassword=password.getEditText().getText().toString().trim();
        if(inputPassword.isEmpty()){
            password.setError("Password cannot be Empty");
            return false;
        }

        else if (!Password_Pattern.matcher(inputPassword).matches()){
            password.setError("Password is too weak.");
            return false;
        }

        else{
            password.setError(null);
            return true;
        }
    }

    public boolean confomMobile(){
        String inputMobile=mobile.getEditText().getText().toString().trim();
        if(inputMobile.isEmpty()){
            mobile.setError("Mobile cannot be Empty");
            return false;
        }
        else if (!MOBILE_NUMBER.matcher(inputMobile).matches()){
            mobile.setError("invalid mobile number.");
            return false;
        }


        else{
            mobile.setError(null);
            return true;
        }
    }

    public boolean confomEmail(){
        String inputEmail=email.getEditText().getText().toString().trim();
        if(inputEmail.isEmpty()){
            email.setError("Email cannot be Empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){
            email.setError("Email id is not valid");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }

    public boolean confomUsername(){
        String inputUsername=username.getEditText().getText().toString().trim();
        if(inputUsername.isEmpty()){
            username.setError("Username cannot be Empty");
            return false;
        }
        else{
            username.setError(null);
            return true;
        }
    }



    public boolean confomCollege(){
        String inputUsername=college.getEditText().getText().toString().trim();
        if(inputUsername.isEmpty()){
            college.setError("Collage/School name cannot be Empty");
            return false;
        }
        else{
            college.setError(null);
            return true;
        }
    }


}
