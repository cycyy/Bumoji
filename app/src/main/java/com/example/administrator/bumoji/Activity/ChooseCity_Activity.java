package com.example.administrator.bumoji.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.administrator.bumoji.R;
import com.example.administrator.bumoji.Tools.ArrangeData;
import com.example.administrator.bumoji.Tools.GaodeApiTool;
import com.example.administrator.bumoji.Tools.GsonUtil;
import com.example.administrator.bumoji.Tools.Httptool;
import com.example.administrator.bumoji.Tools.NowWeather;
import com.example.administrator.bumoji.Tools.PreferencesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/11.
 */

public class ChooseCity_Activity extends Activity {
    private boolean flag;
    private SwipeMenuListView chooseCity_ListView;
    private String newcity;
    private Map<String,NowWeather> map;
    private List<String> list1;
    private Set<String> set;
    private Intent intent;
    private ArrangeData arangedata;
    private ImageButton addCity_Button;
    private PreferencesUtil preferencesUtil;
    private SimpleAdapter listAdapter;
    List<Map<String,Object>> mData;
    Context context;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosecity_layout);
        intent = getIntent();
        chooseCity_ListView= (SwipeMenuListView ) findViewById(R.id.choosecity_ListView);
        addCity_Button= (ImageButton) findViewById(R.id.addCity_ImageButton);
        flag=false;
        map=(Map<String,NowWeather>)intent.getSerializableExtra("nowWeatherMap");
        list1=(List<String>)intent.getSerializableExtra("list");
        setItem();
        Decorate();
        context=getApplicationContext();
        preferencesUtil=new PreferencesUtil(context);
        chooseCity_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String str= list1.get(position);
              preferencesUtil.savechoose(str);
                finish();
            }
        });
        chooseCity_ListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String str= list1.get(position);
                list1.remove(position);
                mData = arangedata.getList2(map, list1);
                listAdapter = new SimpleAdapter(getApplicationContext(), mData,
                        R.layout.city_list, new String[]{"time", "city", "temperature"},
                        new int[]{R.id.time_TextView, R.id.city_TextView, R.id.temp_TextView});
                chooseCity_ListView.setAdapter(listAdapter);
                set = new HashSet<String>();
                set.addAll(list1);
                preferencesUtil.save(set);
                return false;
            }
        });
        addCity_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ChooseCity_Activity.this,AddCity_Activity.class);
                flag=true;
                startActivity(intent);
            }
        });

    }
    private int dp2px(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag) {
            newcity = (preferencesUtil.getPreferences1());
            if (newcity != null &&!newcity.equals("")) {
                preferencesUtil.delete();
                Httptool httptool = new Httptool();
                httptool.getHttp(GaodeApiTool.getNowWeather(newcity), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        NowWeather nowWeather = GsonUtil.parseJsonWithGson(response.body().string(), NowWeather.class);
                        map.put(newcity, nowWeather);
                        list1.add(newcity);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mData = arangedata.getList2(map, list1);
                                listAdapter = new SimpleAdapter(getApplicationContext(), mData,
                                        R.layout.city_list, new String[]{"time", "city", "temperature"},
                                        new int[]{R.id.time_TextView, R.id.city_TextView, R.id.temp_TextView});
                                chooseCity_ListView.setAdapter(listAdapter);
                            }
                        });
                    }
                });


            }

        }
    }

    public void setItem(){
        SwipeMenuCreator creator = new SwipeMenuCreator(){
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.RED));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("删除");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

            }
        };
        chooseCity_ListView.setMenuCreator(creator);
    }

    public void Decorate(){
        arangedata=new ArrangeData();

        mData = arangedata.getList2(map,list1);
         listAdapter=new SimpleAdapter(getApplicationContext(),mData,
                 R.layout.city_list,new String[]{"time","city","temperature"},
                 new int[]{R.id.time_TextView,R.id.city_TextView,R.id.temp_TextView});
        chooseCity_ListView.setAdapter(listAdapter);


    }


}
