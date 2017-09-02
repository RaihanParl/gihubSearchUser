package com.nacoda.cermaticom;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nacoda.cermaticom.Adapter.MainAdapter;
import com.nacoda.cermaticom.NetworkUtils.GsonGithub;
import com.nacoda.cermaticom.NetworkUtils.Server;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.rcMain)
    RecyclerView rcMain;
    GsonGithub gsonGithub;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    MainAdapter adapter;
    String kata;
    @InjectView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.search);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(5);
        }
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
            }
        });

    }

    void getData() {
        rcMain.setVisibility(View.GONE);
        refresh.setRefreshing(true);
        stringRequest = new StringRequest(Request.Method.GET, Server.BASE_RECIPE_URL + kata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    if (
                            String.valueOf(new JSONObject(response).getString("total_count")).equals("0")
                            ) {
                        Toast.makeText(MainActivity.this, "user not found", Toast.LENGTH_SHORT).show();
                        refresh.setRefreshing(false);
                    }else {
                        rcMain.setVisibility(View.VISIBLE);
                        LinearLayoutManager linearmanager = new LinearLayoutManager(MainActivity.this);
                        rcMain.setLayoutManager(linearmanager);
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        gsonGithub = gson.fromJson(response, GsonGithub.class);
                        adapter = new MainAdapter(gsonGithub.bio, MainActivity.this);
                        rcMain.setAdapter(adapter);
                        refresh.setRefreshing(false);

                    }
                } catch (Exception i) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search Github User");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("")||query.equals(null)){
                    Toast.makeText(MainActivity.this, "please insert the query first", Toast.LENGTH_SHORT).show();
                }
                kata = query;
                getData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });
        return true;
    }
}
