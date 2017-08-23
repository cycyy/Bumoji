package com.example.administrator.bumoji.Tools;

import android.content.Context;

import com.example.administrator.bumoji.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/8/14.
 */

public class CityCodeTool {
    public static HashMap<String, String> codeMaps;
    public static HashMap<String, String> nameMaps;
    public static ArrayList<String> names;
    public static void init(Context context) {
        codeMaps = new HashMap<>();
        nameMaps = new HashMap<>();
        names = new ArrayList<>();
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.city_codes);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                line = line.replace("\uFEFF","");
                String[] strings = line.split("\t");
                nameMaps.put(strings[0], strings[1]);
                codeMaps.put(strings[1], strings[0]);
                Set<String> string = nameMaps.keySet();
                names.add(strings[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String name2code(String name) {
        return nameMaps.get(name);
    }
    public static String code2name(String code) {
        return codeMaps.get(code);
    }
    public static ArrayList<String> findCity(String keyword) {
        ArrayList<String> results = new ArrayList<>();
        if (keyword == null || keyword.equals("")){
            return results;
        }
        for(String name : names) {
            if(name.contains(keyword))
                results.add(name);
        }
        return results;
    }
}
