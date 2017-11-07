package com.smim.smimandroid.http;

import android.content.Context;
import android.widget.Toast;

import com.smim.smimandroid.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kakoapps on 2016-04-29.
 */
public class HttpsManager {

    private final String TAG = getClass().getSimpleName();
    private final String CHARSET = "utf-8";

    private static Retrofit retrofit;

    public static HttpService service;

    private Context mContext;
    public HttpsManager(Context context){
        mContext = context;
    }

    public static void setService(String baseUrl){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if(service == null){
            service = retrofit.create(HttpService.class);
        }
    }


//    public boolean networkCheck(){
//        if(!AppManager.isNetworkCoonnection(mContext)){
//            setToast(mContext.getString(R.string.network_not_connect));
//            return false;
//        }
//        return true;
//    }

    public void setToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private String decodeResult(String str){
        try {
            return URLDecoder.decode(str, CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void onError(String msg){
//        DialogView.progressDialogClose();
        setToast(msg);
    }

    public void onError(){
//        DialogView.progressDialogClose();
        setToast(mContext.getString(R.string.network_error));
    }

}
