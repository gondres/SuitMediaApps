package com.projectpertama.suitmediaapps.ThirdScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.projectpertama.suitmediaapps.DataItem;
import com.projectpertama.suitmediaapps.FirstScreen.FirstActivity;
import com.projectpertama.suitmediaapps.ListUserResponse;
import com.projectpertama.suitmediaapps.R;
import com.projectpertama.suitmediaapps.RestClient;
import com.projectpertama.suitmediaapps.SecondScreen.SecondActivity;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    ImageView back;
    @BindView(R.id.rvListUser)
    RecyclerView rvListUser;


    private List<DataItem> listItem;
    private ThirdActivityAdapter adapter;
    private ProgressBar pbLoading;
    private ThirdActivityAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);

        initialize();
       //fetchData method
        fetchData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent second = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(second);
            }
        });

    }

    private void initialize() {
        back = findViewById(R.id.backButton);
        pbLoading = findViewById(R.id.progressBar);
    }


    private void fetchData() {
        //Loading ketika data sedang diambil
        pbLoading.setVisibility(View.VISIBLE);

        //Mengambil api dari getList() yang ada di PersonAPI
        RestClient.getService().getList().enqueue(new Callback<ListUserResponse>() {
            @Override
            public void onResponse(Call<ListUserResponse> call, Response<ListUserResponse> response) {
                pbLoading.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    listItem = response.body().getData();
                    setOnClickListener();
                    adapter = new ThirdActivityAdapter(listItem, ThirdActivity.this,listener);
                    rvListUser.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvListUser.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(Call<ListUserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to Connect", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setOnClickListener() {
        listener = new ThirdActivityAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                getIntent().putExtra("first_name",listItem.get(position).getFirstName());
            }
        };
    }
}



