package com.bogoslovov.kaloyan.beer;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setElevation(0);
        }
        setContentView(R.layout.activity_main);
        getData();
        initDrawer();
        initTabLayoutAndViewPager();

    }

    private void getData(){
        AsyncTask backgroundTask = new AsyncTask<Object,Void, Response>(){

            @Override
            protected Response doInBackground(Object... params) {
                String url = Constants.BASE_URL+"beer/oeGSxs"+Constants.BREWERY_DB_KEY+Constants.FORMAT;
                System.out.println(url);
                Response response=null;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(Response response) {

                int responseCode = response.code();
                switch(responseCode){
                    case 200:
                        System.out.println("response 200");
                        try {
                            System.out.println(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    default:
                        Toast.makeText(MainActivity.this,"error:"+ responseCode , Toast.LENGTH_LONG).show();
                }


            }
        };
        backgroundTask.execute();
    }

    private void initDrawer(){
        String[] drawer_list_items = getResources().getStringArray(R.array.drawer_list_items);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.drawer_list_item,
                R.id.settings_text_view,
                drawer_list_items
        );

        ListView drawerList = (ListView) findViewById(R.id.left_drawer_list_view);
        drawerList.setAdapter(arrayAdapter);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTabLayoutAndViewPager(){
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(),this);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        viewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}