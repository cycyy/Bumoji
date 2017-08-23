package com.example.administrator.bumoji.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bumoji.Fragment.MakeFragment;
import com.example.administrator.bumoji.R;
import com.example.administrator.bumoji.Tools.GaodeApiTool;
import com.example.administrator.bumoji.Tools.GsonUtil;
import com.example.administrator.bumoji.Tools.Httptool;
import com.example.administrator.bumoji.Tools.NowWeather;
import com.example.administrator.bumoji.Tools.PreferencesUtil;
import com.example.administrator.bumoji.Tools.Weather;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TextView city_TextView;
    private ImageButton chooseCity_ImageButton;
    public Weather weather;
    public NowWeather nowWeather;
    public  List<String> list;
    public Map<String,Weather> weatherMap;
    public Map<String,NowWeather> nowWeatherMap;
    public int num=0;
    public int position;
    public PreferencesUtil preferencesUtil;
    public Set set;
    private boolean flage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flage=false;
        weatherMap=new HashMap<>();
        nowWeatherMap=new HashMap<>();
        list = new ArrayList();

       inithttp();
        chooseCity_ImageButton = (ImageButton) findViewById(R.id.chooseCity_ImageButton);
        chooseCity_ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                inithttp();
                flage=true;
                Intent intent = new Intent();
                intent.putExtra("list", (Serializable) list);
                intent.putExtra("nowWeatherMap", (Serializable) nowWeatherMap);
                intent.setClass(MainActivity.this, ChooseCity_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        if (flage) {
            list.clear();
            nowWeatherMap.clear();
            weatherMap.clear();
            num = 0;
            list.addAll(preferencesUtil.getPreferences());
            mSectionsPagerAdapter.notifyDataSetChanged();
            doHttp();
            position=list.indexOf(preferencesUtil.getchoose());

        }
        super.onResume();
    }
    public void inithttp() {
        Context context = getApplicationContext();
        set = new HashSet();
        set.add("110000");
        set.add("210100");

        preferencesUtil = new PreferencesUtil(context);

        preferencesUtil.save(set);
        set = preferencesUtil.getPreferences();
        list.addAll(set);
        doHttp();
    }


    public void doHttp() {
            final Httptool httptool = new Httptool();
            for (int i = 0; i < list.size(); i++) {
                final int finalI = i;
                httptool.getHttp(GaodeApiTool.getNowWeather(list.get(i)), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @Override
                    public void onResponse(final Call call, Response response) throws IOException {
                        if (null != response.cacheResponse()) {
                            String str = response.cacheResponse().toString();
                        } else {
                            String string = response.body().string();
                            System.out.println(string);
                            nowWeatherMap.put(list.get(finalI), GsonUtil.parseJsonWithGson(string, NowWeather.class));
                            httptool.getHttp(GaodeApiTool.getWeather(list.get(finalI)), new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    weatherMap.put(list.get(finalI), GsonUtil.parseJsonWithGson(response.body().string(), Weather.class));
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ifSuccess();
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            }
        }


    public synchronized void ifSuccess(){
        num++;
        if(num==list.size()){
             createViewPager();
            if (flage) {
                   mViewPager.setCurrentItem(position);
            }
        }
    }

    public void createViewPager() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void run() {
//        weather= GaodeApiTool.getWeather("沈阳");
//    }

    /**
     * A placeholder fragment containing a simple view.
     */




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter{

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return MakeFragment.newInstance(position + 1,weatherMap.get(list.get(position)),nowWeatherMap.get(list.get(position)));
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return null;
        }
    }
}
