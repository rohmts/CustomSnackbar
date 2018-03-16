package com.wordpress.pesanmerpati.customsnackbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wordpress.pesanmerpati.customsnackbar.Adapters.ItemsAdapter;
import com.wordpress.pesanmerpati.customsnackbar.Interfaces.RequestInterface;
import com.wordpress.pesanmerpati.customsnackbar.Model.Items;
import com.wordpress.pesanmerpati.customsnackbar.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Items> items = new ArrayList<>();
    RecyclerView mRecyclerViewUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewUsers = findViewById(R.id.recyclerView_users);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewUsers.setLayoutManager(mLinearLayoutManager);

        loadItemsUser();

    }

    private void loadItemsUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<User> request = requestInterface.getDataItems();

        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                items = response.body().getItems();
                ItemsAdapter mAdapter = new ItemsAdapter(MainActivity.this,items);
                mRecyclerViewUsers.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }
}
