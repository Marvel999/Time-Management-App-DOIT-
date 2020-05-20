package com.example.doit.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.doit.Main_app;
import com.example.doit.R;
import com.example.doit.ui.data.SqlHepler;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.regex.Pattern;


public class LoginFragment extends Fragment {
    TextView signupclick;
    TextView signup;
    TextInputLayout mobile;
    TextInputLayout password;
    SqlHepler sqlHepler;
    SQLiteDatabase sqLiteDatabase,sqLiteDatabase1;
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
         View root =inflater.inflate(R.layout.fragment_login, container, false);
        Button login=root.findViewById(R.id.signin_btn);
        signupclick=root.findViewById(R.id.singintextclickable);
        mobile=root.findViewById(R.id.input_Mobileno);
        password=root.findViewById(R.id.input_password);
        sqlHepler=new SqlHepler(getActivity());
        sqLiteDatabase=sqlHepler.getReadableDatabase();
        sqLiteDatabase1=sqlHepler.getWritableDatabase();




        login.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if((!confomPassword() | !confomMobile()) && !setdataauth()){
                         return;
                     }
                     else {
                         Intent intent = new Intent(getActivity(), Main_app.class);
                         startActivity(intent);
                         getActivity().finish();
                     }

                 }
             });




             signupclick.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                Fragment fragment = new SignupFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContenersignup, fragment);
                ft.disallowAddToBackStack();
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
            password.setError("Typo Mistake for password.");
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

    public Boolean setdataauth(){
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT MOBILE,PASSWORD FROM AUTH",new String[]{});

        if(cursor != null){
            cursor.moveToFirst();
        }
        StringBuilder s=new StringBuilder();
        do{
            String mobile_no=cursor.getString(0);
            String password_pass=cursor.getString(1);
            Log.e("DATABASE",""+s);

            s.append("Mobile number:- "+mobile_no+"   "+"Password_text:-"+password_pass+"\n");
            if(mobile_no.equals(mobile.getEditText().getText().toString()) && mobile_no.equals(mobile.getEditText().getText().toString()))
                return true;
            else
                return false;

        }
        while (cursor.moveToNext());


    }
}
