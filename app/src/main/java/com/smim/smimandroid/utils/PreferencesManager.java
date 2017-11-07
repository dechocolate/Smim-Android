package com.smim.smimandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.smim.smimandroid.Const;
import com.smim.smimandroid.R;
import com.smim.smimandroid.dto.UserInfo;


/**
 * Created by John on 2015-12-09.
 */


public class PreferencesManager implements Const{

    private Context mContext;
    private SharedPreferences pref;
    private SharedPreferences.Editor saver;


    public PreferencesManager(Context paramContext) {
        mContext = paramContext;
        pref = paramContext.getSharedPreferences(mContext.getString(R.string.app_name), Activity.MODE_PRIVATE);
        saver = pref.edit();
    }

    public void setLoginUser(UserInfo value){

        saver.putString("user_no", value.user_no);
        saver.putString("user_id", value.user_id);
        saver.putString("user_nick", value.user_nick);
        saver.putString("user_hp", value.user_hp);
        saver.putString("user_photo", value.user_photo);
        saver.putString("user_point", value.user_point);
        saver.putString("user_bank_nm", value.user_bank_nm);
        saver.putString("user_bank_num", value.user_bank_num);
        saver.putString("user_bank_own", value.user_bank_own);
        saver.putString("f_push_chk1", value.f_push_chk1);
        saver.putString("f_push_chk2", value.f_push_chk2);
        saver.putString("f_push_chk3", value.f_push_chk3);
        saver.putString("f_push_chk4", value.f_push_chk4);
        saver.putString("f_push_chk5", value.f_push_chk5);
        saver.commit();
    }

    public UserInfo getLoginUser(){
        UserInfo user = new UserInfo();
        user.user_no = pref.getString("user_no", "");
        user.user_id = pref.getString("user_id", "");
        user.user_nick = pref.getString("user_nick", "");
        user.user_hp = pref.getString("user_hp", "");
        user.user_photo = pref.getString("user_photo", "");
        user.user_point = pref.getString("user_point", "");
        user.user_bank_nm = pref.getString("user_bank_nm", "");
        user.user_bank_num = pref.getString("user_bank_num", "");
        user.user_bank_own = pref.getString("user_bank_own", "");
        user.f_push_chk1 = pref.getString("f_push_chk1", "");
        user.f_push_chk2 = pref.getString("f_push_chk2", "");
        user.f_push_chk3 = pref.getString("f_push_chk3", "");
        user.f_push_chk4 = pref.getString("f_push_chk4", "");
        user.f_push_chk5 = pref.getString("f_push_chk5", "");
        return user;
    }


    public void setLoginUserClear(){

        saver.putString("user_no", "");
        saver.putString("user_id", "");
        saver.putString("user_nick", "");
        saver.putString("user_hp", "");
        saver.putString("user_photo", "");
        saver.putString("user_point", "");
        saver.putString("user_bank_nm", "");
        saver.putString("user_bank_num", "");
        saver.putString("user_bank_own", "");
        saver.putString("f_push_chk1", "");
        saver.putString("f_push_chk2", "");
        saver.putString("f_push_chk3", "");
        saver.putString("f_push_chk4", "");
        saver.putString("f_push_chk5", "");
        saver.commit();
    }


}