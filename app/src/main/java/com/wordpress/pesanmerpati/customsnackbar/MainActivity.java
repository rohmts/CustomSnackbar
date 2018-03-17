package com.wordpress.pesanmerpati.customsnackbar;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wordpress.pesanmerpati.customsnackbar.Model.Items;
import com.wordpress.pesanmerpati.customsnackbar.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wordpress.pesanmerpati.customsnackbar.Constants.CONNECTIVITY_ACTION;

public class MainActivity extends AppCompatActivity {

    List<Items> items = new ArrayList<>();
    RecyclerView mRecyclerViewUsers;
    ConstraintLayout rootLayout;

    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewUsers = findViewById(R.id.recyclerView_users);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewUsers.setLayoutManager(mLinearLayoutManager);
        rootLayout = findViewById(R.id.rootLayout);

        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);

        if (NetworkUtil.getConnectivityStatus(MainActivity.this) > 0)
            loadItemsUser();
        else {
            Snackbar snackbar = Snackbar.make(rootLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("TURN ON", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName(
                                    "com.android.settings",
                                    "com.android.settings.Settings$DataUsageSummaryActivity"));
                            startActivity(intent);
                        }
                    });
            snackbar.setActionTextColor(Color.GREEN);
            View snackbarView = snackbar.getView();
            TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }
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
                items = response.body().getItemsList();
                ItemsAdapter mAdapter = new ItemsAdapter(MainActivity.this, items);
                mRecyclerViewUsers.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Snackbar.make(rootLayout, t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItemsUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                if (NetworkUtil.getConnectivityStatus(MainActivity.this) == 0) {
                    Snackbar snackbar = Snackbar.make(rootLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("TURN ON", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName(
                                            "com.android.settings",
                                            "com.android.settings.Settings$DataUsageSummaryActivity"));
                                    startActivity(intent);
                                }
                            });
                    snackbar.setActionTextColor(Color.GREEN);
                    View snackbarView = snackbar.getView();
                    TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();
                } else
                    loadItemsUser();
                break;
            default:
                break;
        }
        return true;
    }
}
