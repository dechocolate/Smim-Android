package com.smim.smimandroid;


import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;


/**
 * Created by kakoapps on 2016-01-27.
 */
public class SmimApp extends Application implements Const {

    private static SmimApp appInstance;

    public Activity mainActivity;


    //필요 데이터 선언
    public Typeface typefaceNanumR = null;
    public Typeface typefaceNanumB = null;

    //GCM 토큰값
    public String strGcmToken = "";


    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;


    }

    public static SmimApp getInstance(){
        return appInstance;
    }

    public void setMainAct(Activity act) {
        mainActivity = act;
    }

    /**
     * @brief 폰트 셋팅
     */
    public void setFont(){
        // 폰트를 쓰레드로 돌린다.
        AsyncTask<Void, Integer, Void> mTask = new AsyncTask<Void, Integer, Void>() {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected Void doInBackground(Void... params) {

//                if (typefaceNanumR == null) {
//                    typefaceNanumR = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
//                }
//
//                if (typefaceNanumB == null) {
//                    typefaceNanumB = Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");
//                }

                return null;
            }

            // publishProgress()
            @Override
            protected void onProgressUpdate(Integer... progress) {
                // mProgress.setProgress(progress[0]);
            }

            @Override
            protected void onPostExecute(Void result) {

            }

            @Override
            protected void onCancelled() {
                // dialog.dismiss();
            }
        };
        mTask.execute();
    }


    public Typeface getTypefaceNanumRegularFont(){
        if(typefaceNanumR != null){
            return typefaceNanumR;
        }else{
            setFont();
            return typefaceNanumR;
        }
    }

    public Typeface getTypefaceNanumBoldFont(){
        if(typefaceNanumB != null){
            return typefaceNanumB;
        }else{
            setFont();
            return typefaceNanumB;
        }
    }


    /**
     * @brief sharedpreperence 데이터
     */
    public void setSharedPreferencesData(String key, boolean value) {
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setSharedPreferencesData(String key, String value) {
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void setSharedPreferencesData(String key, int value) {
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public boolean getSharedPreferencesData(String key, boolean defaultKey){
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        return prefs.getBoolean(key, defaultKey);
    }

    public String getSharedPreferencesData(String key, String defaultKey){
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        return prefs.getString(key, defaultKey);
    }

    public int getSharedPreferencesData(String key, int defaultKey){
        SharedPreferences prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        return prefs.getInt(key, defaultKey);
    }


}
