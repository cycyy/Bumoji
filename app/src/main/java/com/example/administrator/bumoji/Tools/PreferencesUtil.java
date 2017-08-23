package com.example.administrator.bumoji.Tools;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/17.
 */

public class PreferencesUtil {
    private Context context;
    public PreferencesUtil(Context context) {
        this.context = context;
    }
    public void save(Set citycode){
        //通过Context.getSharedPreferences方法获取SharedPreferences对象，参数分别为存储的文件名和存储模式
        SharedPreferences preferences = context.getSharedPreferences("citylist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("citycode",citycode);
        editor.commit();
    }

    public Set<String> getPreferences(){
        Set<String> params = new HashSet<>();
        SharedPreferences preferences = context.getSharedPreferences("citylist", Context.MODE_PRIVATE);
        params = preferences.getStringSet("citycode",new HashSet<String>());
        return params;
    }
    public void save(String citycode){
        //通过Context.getSharedPreferences方法获取SharedPreferences对象，参数分别为存储的文件名和存储模式
        SharedPreferences preferences = context.getSharedPreferences("citylist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("newcity",citycode);

        editor.commit();
    }
    public void saveposition(int position){
        SharedPreferences preferences = context.getSharedPreferences("position", Context.MODE_APPEND);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("position",position);
        editor.commit();
    }

    public int getposition(){
        int position;
        SharedPreferences preferences = context.getSharedPreferences("position", Context.MODE_APPEND);
        position = preferences.getInt("position",0);
        return position;
    }

    public String getPreferences1(){
        String params ;
        SharedPreferences preferences = context.getSharedPreferences("citylist", Context.MODE_PRIVATE);
        params = preferences.getString("newcity","");

        return params;
    }

    public void delete(){
        SharedPreferences preferences=context.getSharedPreferences("citylist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("newcity");
        editor.apply();
    }
    public void savechoose(String choosecity){
        SharedPreferences preferences = context.getSharedPreferences("choosecity", Context.MODE_APPEND);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("choosecity",choosecity);
        editor.commit();
    }

    public String getchoose(){
        String choosecity;
        SharedPreferences preferences = context.getSharedPreferences("choosecity", Context.MODE_APPEND);
        choosecity = preferences.getString("choosecity","");
        return choosecity;
    }
}
