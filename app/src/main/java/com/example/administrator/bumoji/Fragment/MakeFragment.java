package com.example.administrator.bumoji.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.bumoji.R;
import com.example.administrator.bumoji.Tools.ArrangeData;
import com.example.administrator.bumoji.Tools.NowWeather;
import com.example.administrator.bumoji.Tools.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/12.
 */

public  class MakeFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String WEATHER = "weather";
    private static final String NOWWEATHER = "nowWeather";
    private Weather weather;
    private NowWeather nowWeather;
    private ArrangeData arangedata;


    public MakeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MakeFragment newInstance(int sectionNumber, Weather weather, NowWeather nowWeather) {
        MakeFragment fragment = new MakeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(WEATHER,weather);
        args.putSerializable(NOWWEATHER,nowWeather);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weather= (Weather) getArguments().getSerializable(WEATHER);
        nowWeather = (NowWeather) getArguments().getSerializable(NOWWEATHER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Decorate(rootView);
//            TextView textView = (TextView) rootView.findViewById(R.id.city_textView);
//            textView.setText(weather.getForecasts().get(0).getCity());

        return rootView;
    }

    private void Decorate(View view) {
        if(weather!=null&&nowWeather!=null) {

            TextView city_textView = (TextView) view.findViewById(R.id.city_textView);
            TextView weather_textView = (TextView) view.findViewById(R.id.weather_textView);
            TextView temperature_textView = (TextView) view.findViewById(R.id.temperature_textView);
            TextView week_day_textView = (TextView) view.findViewById(R.id.week_day_textView);
            TextView maxtemperature_textView = (TextView) view.findViewById(R.id.maxtemperature_textView);
            TextView mintemperature_textView = (TextView) view.findViewById(R.id.mintemperature_textView);
            city_textView.setText(weather.getForecasts().get(0).getCity());
            weather_textView.setText(nowWeather.getLives().get(0).getWeather());
            temperature_textView.setText(nowWeather.getLives().get(0).getTemperature() + "°");
            week_day_textView.setText(setWeek(weather.getForecasts().get(0).getCasts().get(0).getWeek()) + "(今天)");
            maxtemperature_textView.setText(weather.getForecasts().get(0).getCasts().get(0).getDaytemp());
            mintemperature_textView.setText(weather.getForecasts().get(0).getCasts().get(0).getNighttemp());
            ListView listview = (ListView) view.findViewById(R.id.otherday_ListView);
            arangedata = new ArrangeData();
            setBackground(view, nowWeather.getLives().get(0).getWeather());
            ListAdapter listadapter = new SimpleAdapter(getContext(), arangedata.getList1(weather), R.layout.futureday_list, new String[]{"week", "dayweather", "daytemp", "nighttemp"},
                    new int[]{R.id.day1_textView, R.id.day_textView, R.id.maxtemperature_textView1, R.id.mintemperature_textView1});
            listview.setAdapter(listadapter);
        }
    }


    public String setWeek(String week) {
        switch (week){
        case "1":return "星期一";
        case "2":return "星期二";
        case "3":return "星期三";
        case "4":return "星期四";
        case "5":return "星期五";
        case "6":return "星期六";
        case "7":return "星期日";
        default:return "cyy大帅比";
        }
    }

    public void setBackground(View view,String weahther){
         LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.mainbackground);
       switch (weahther){
           case "晴":linearLayout.setBackgroundResource(R.drawable.sunny);break;
           case "多云":linearLayout.setBackgroundResource(R.drawable.cloud);break;
           case "阴":linearLayout.setBackgroundResource(R.drawable.overcast_sky);break;
           case "小雪:":linearLayout.setBackgroundResource(R.drawable.snow);break;
           default:linearLayout.setBackgroundResource(R.drawable.sunny);break;
       }
    }
}
