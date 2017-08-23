package com.example.administrator.bumoji;

import com.example.administrator.bumoji.Tools.GaodeApiTool;
import com.example.administrator.bumoji.Tools.Weather;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Weather weather=GaodeApiTool.getWeather("沈阳");
        System.out.print("");
    }
}