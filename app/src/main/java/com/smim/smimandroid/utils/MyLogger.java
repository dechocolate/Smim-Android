package com.smim.smimandroid.utils;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by kakoapps on 2016-01-28.
 */
public class MyLogger {

    private static boolean logEnabled = false;
    private static String TAG = "JHTEST";

    public static void LogD(String tag, String msg)
    {
        if(logEnabled)
            Log.d(tag, ""+msg);
    }

    public static void LogD(String msg)
    {
        if(logEnabled)
            Log.d(TAG, ""+msg);
    }

    public static void LogE(String tag, String msg)
    {
        if(logEnabled)
            Log.e(tag, "" + msg);
    }


    public static void LogE(String msg)
    {
        if(logEnabled)
            Log.e(TAG, "" + msg);
    }


    public static void LogUrl(String tag, String msg)
    {
        if(logEnabled)
            Log.e(tag, "" + msg);
    }

    public static void LogUrl(String tag, String url, HashMap<String,Object> map)
    {
        if(logEnabled){

            String params = "";

            boolean isFirst = true;

            for( String key : map.keySet() ){
                if(isFirst){
                    params += "?"+ key +"="+map.get(key);
                    isFirst = false;
                }else{
                    params += "&"+ key +"="+map.get(key);
                }

            }

            Log.e(tag, "" + url + params);
        }
    }


    public static void LogEnable()
    {
        logEnabled = true;
    }

    public static void LogDisable()
    {
        logEnabled = false;
    }

}
