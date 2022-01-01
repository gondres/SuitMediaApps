package com.projectpertama.suitmediaapps.FirstScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.projectpertama.suitmediaapps.R;
import com.projectpertama.suitmediaapps.SecondScreen.SecondActivity;

public class FirstActivity extends AppCompatActivity {

    private static final String KEY_NAME = "name";
    private static final String SHARED_PREFF_NAME = "mypref";
    SharedPreferences sharedPref;
    String nama,palindrome;
    EditText et_nama,et_palindrome;
    Button check,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //Init
        initialize();




        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_palindrome.length()<=0){
                    et_palindrome.requestFocus();
                    et_palindrome.setError("Cannot be blank!");
                }else{
                    CheckPalindrome();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }

    private void validate() {
        if(et_nama.length()<=0){
            et_nama.requestFocus();
            et_nama.setError("Please Insert Your Name");
        }else{
            SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(KEY_NAME, et_nama.getText().toString());
                editor.apply();
                startActivity(new Intent(getApplicationContext(),SecondActivity.class));
        }
    }

    private void CheckPalindrome() {
        String poli = "";
        palindrome = et_palindrome.getText().toString();
        for(int i=palindrome.length()-1; i>-1; i--){
            char c = palindrome.charAt(i);
            poli += String.valueOf(c);
        }
        if(palindrome.toLowerCase().equals(poli.toLowerCase())){
            Toast.makeText(getApplicationContext(), ""+palindrome+" is Palindrome", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), ""+palindrome+" is not Palindrome", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFF_NAME, MODE_PRIVATE);
        et_nama = findViewById(R.id.et_Name);
        check = findViewById(R.id.btn_check);
        next = findViewById(R.id.btn_next);
        et_palindrome = findViewById(R.id.et_Palindrome);
    }
}