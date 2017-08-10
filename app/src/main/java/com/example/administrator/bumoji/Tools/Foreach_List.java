package com.example.administrator.bumoji.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/10.
 */

public class Foreach_List  {
    public void a(){
        String data=HttpRequest.sendGet(" http://restapi.amap.com/v3/weather/weatherInfo", "city=110101&key=2ffdc2d7a0c8b134d6f7ef2de0391c0a&extensions=all");
        System.out.print(data);
    }
    private List<Map<String,Object>>getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        String data=HttpRequest.sendGet(" http://restapi.amap.com/v3/weather/weatherInfo", "city=110101&key=2ffdc2d7a0c8b134d6f7ef2de0391c0a&extensions=all");

        return list;
    }
}
