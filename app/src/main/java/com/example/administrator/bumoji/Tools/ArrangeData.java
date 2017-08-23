package com.example.administrator.bumoji.Tools;

import android.content.Intent;

import com.example.administrator.bumoji.Fragment.MakeFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ArrangeData {
    List<Map<String,Object>> list1;
    List<Map<String,Object>> list2;
    private Intent intent;
    public List<Map<String,Object>> getList1(Weather weather){
        list1=new ArrayList<>();
        for (int i=1;i<weather.getForecasts().get(0).getCasts().size();i++){
            Map<String,Object>  map1=new HashMap<String,Object>();
            map1.put("week",getWeek(weather.getForecasts().get(0).getCasts().get(i).getWeek()));
            map1.put("daytemp",weather.getForecasts().get(0).getCasts().get(i).getDaytemp());
            map1.put("nighttemp",weather.getForecasts().get(0).getCasts().get(i).getNighttemp()+"°");
            map1.put("dayweather",weather.getForecasts().get(0).getCasts().get(i).getDayweather());
            list1.add(map1);
        }
        return list1;
    }

    public List<Map<String,Object>> getList2(Map<String,NowWeather> map,List<String> citycode){
        list2=new ArrayList<>();
         NowWeather noweather;
        for (int i=0;i<citycode.size();i++){
            Map<String,Object>  map2=new HashMap<String,Object>();
            noweather= map.get(citycode.get(i));
            map2.put("time",getSysTime());
            map2.put("city",noweather.getLives().get(0).getCity());
            map2.put("temperature",noweather.getLives().get(0).getTemperature()+"°");
            list2.add(map2);
        }
        return list2;
    }
    public String getWeek(String week){
        int weekNumber = Integer.parseInt(week);
        switch (weekNumber){
            case 1:return "星期一";
            case 2:return "星期二";
            case 3:return "星期三";
            case 4:return "星期四";
            case 5:return "星期五";
            case 6:return "星期六";
            case 7:return "星期日";
            default:return "cyy大帅比";
        }
    }


    public String getSysTime() {
        SimpleDateFormat sDateFormat    =  new    SimpleDateFormat("hh:mm");
        String date =  sDateFormat.format(new java.util.Date());
        return date;
    }
}
