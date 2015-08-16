package com.lidan.keylor.musicmaster;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lidan.keylor.musicmaster.BaiduApi.BaiduMusicHelper;
import com.lidan.keylor.musicmaster.BaiduApi.BaiduMusicRequest;
import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicInfo;
import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicList;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Thread.UncaughtExceptionHandler{

    public static final String MUSIC_ID = "id";

    BaiduMusicHelper musicHelper;
    ListView musicListView;
    ArrayAdapter<MusicInfo> arrayAdapter;
    ArrayList<MusicInfo> musicList;
    DrawerLayout drawerLayout;
    ListView drawerList;
    ArrayAdapter<String> drawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread.setDefaultUncaughtExceptionHandler(this);

        //find view
        musicListView = (ListView) findViewById(R.id.lv_main_music_list);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        drawerList = (ListView) findViewById(R.id.lv_drawer);

        //adapter
        drawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new String[]{"setting","music","personal info"});



        arrayAdapter = new ArrayAdapter<MusicInfo>(this, android.R.layout.simple_list_item_1){
        };

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name ,R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerList.setBackgroundColor(Color.WHITE);
            }
        };
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerList.setAdapter(drawerAdapter);
        musicListView.setAdapter(arrayAdapter);


        //获取数据
        musicHelper = BaiduMusicHelper.getBaiduMusicHelper();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        BaiduMusicRequest<MusicList> musicListBaiduMusicRequest =
                new BaiduMusicRequest<>(Request.Method.GET, MusicList.class,
                        musicHelper.getHotMusicListURL(0, 50), new Response.Listener<MusicList>() {
                    @Override
                    public void onResponse(MusicList response) {
                        System.out.println(response);
                        musicList = response.song_list;
                        arrayAdapter.clear();
                        arrayAdapter.addAll(musicList);

                        arrayAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("failed");
                    }
                });
        requestQueue.add(musicListBaiduMusicRequest);

        //Listener
        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MusicInfo musicInfo = arrayAdapter.getItem(position);
                Intent musicPalyIntent = new Intent(MainActivity.this, MusicPlayExperiActivity.class);
                musicPalyIntent.putExtra(MUSIC_ID, musicInfo.getSong_id());

                startActivity(musicPalyIntent);

            }
        });
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                if ("music".equals(item)) {
                    Intent toMusic = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(toMusic);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_search){
            System.out.println("搜索");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.out.println(thread);
        System.out.println(ex);
    }
}
