package com.example.administrator.bumoji.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.administrator.bumoji.R;
import com.example.administrator.bumoji.Tools.CityCodeTool;
import com.example.administrator.bumoji.Tools.PreferencesUtil;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/17.
 */

public class AddCity_Activity extends Activity {
    private MaterialSearchBar searchBar;
    PreferencesUtil preferencesUtil;
    private List<String> lastSearches;
    private Set<String> set;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcity_layout);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        context=getApplicationContext();
        CityCodeTool.init(context);
        searchBar.setSuggstionsClickListener(new SuggestionsAdapter.OnItemViewClickListener() {
            @Override
            public void OnItemClickListener(int i, View view) {
                preferencesUtil=new PreferencesUtil(context);
               String str = CityCodeTool.name2code(lastSearches.get(i));
                preferencesUtil.save(str);
                set=preferencesUtil.getPreferences();
                set.add(preferencesUtil.getPreferences1());
                preferencesUtil.save(set);
                finish();
            }

            @Override
            public void OnItemDeleteListener(int i, View view) {

            }
        });

        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastSearches=CityCodeTool.findCity(s.toString());
                searchBar.updateLastSuggestions(CityCodeTool.findCity(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
