package com.projectpertama.suitmediaapps.SecondScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectpertama.suitmediaapps.FirstScreen.FirstActivity;
import com.projectpertama.suitmediaapps.R;
import com.projectpertama.suitmediaapps.ThirdScreen.ThirdActivity;

public class SecondActivity extends AppCompatActivity {


    private static final String KEY_NAME ="name";

    private static final String SHARED_PREFF_NAME = "mypref";
    String name,selected="";
    TextView tvWelcome,tvSelected;
    Button choose;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initialize();

        SharedPreferences pref = getApplicationContext().getSharedPreferences(SHARED_PREFF_NAME, MODE_PRIVATE);
        name = pref.getString(KEY_NAME,null);
        selected = getIntent().getStringExtra("first_name");
        if(selected == null){
            tvSelected.setText("Selected User Name");
        }else{
            tvSelected.append(" is "+selected);
        }


        tvWelcome.setText(name);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ThirdActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent first = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(first);
            }
        });
    }

    private void initialize() {
        tvWelcome = findViewById(R.id.tv_Nama);
        tvSelected = findViewById(R.id.tv_selected);
        choose = findViewById(R.id.btn_choose);
        back = findViewById(R.id.backButton);
    }
}