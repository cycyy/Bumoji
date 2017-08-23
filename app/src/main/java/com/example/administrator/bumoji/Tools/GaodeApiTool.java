package com.example.administrator.bumoji.Tools;

import com.example.administrator.bumoji.Tools.Weather;

/**
 * Created by Administrator on 2017/8/10.
 */
public class GaodeApiTool {
    public static String getCityUrl(String keywords){
        return "http://restapi.amap.com/v3/config/district?key=2ffdc2d7a0c8b134d6f7ef2de0391c0a&subdistrict=0&keywords="+keywords;
    }
    public static String getWeather(String city){
        return "http://restapi.amap.com/v3/weather/weatherInfo?key=2ffdc2d7a0c8b134d6f7ef2de0391c0a&city="+city+"&extensions=all";
    }
    public static String getNowWeather(String city){
        return "http://restapi.amap.com/v3/weather/weatherInfo?key=2ffdc2d7a0c8b134d6f7ef2de0391c0a&city="+city+"&extensions=base";
    }
}
