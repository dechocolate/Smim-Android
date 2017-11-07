package com.smim.smimandroid.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smim.smimandroid.Const;
import com.smim.smimandroid.R;
import com.smim.smimandroid.SmimApp;



public class Dialog_PhotoSelect extends Dialog implements Const {

    private SmimApp mApplicationContext;

    private Context mContext;

    private TextView tv_PhotoTake, tv_PhotoAlbum;
    private LinearLayout ll_Background;

    private Handler mHandler;

    private int iFlag = 0;

    public Dialog_PhotoSelect(Context context, Handler handler) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.mContext = context;
        this.mHandler = handler;

        setContentView(R.layout.dialog_photoselect);
        init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    private void init() {
        mApplicationContext = SmimApp.getInstance();

        tv_PhotoTake = (TextView) findViewById(R.id.textview_dialog_photoselect_take);
        tv_PhotoAlbum = (TextView)findViewById(R.id.textview_dialog_photoselect_select);

        ll_Background = (LinearLayout) findViewById(R.id.linearlayout_dialog_photoselect_background);

        tv_PhotoTake.setOnClickListener(button_clicked);
        tv_PhotoAlbum.setOnClickListener(button_clicked);

        ll_Background.setOnClickListener(button_clicked);


//        settingFont();
    }

    public void settingFont()
    {
    }

    public void setContents(String title)
    {
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        dismiss();
    }

    public void setFlag(int flag)
    {
        iFlag = flag;
    }

    public int getFLAG()
    {
        return iFlag;
    }


    View.OnClickListener button_clicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.textview_dialog_photoselect_take:
                    mHandler.sendEmptyMessage(FLAG_DIALOG_PHOTOTAKE);
                    dismiss();
                    break;
                case R.id.textview_dialog_photoselect_select:
                    mHandler.sendEmptyMessage(FLAG_DIALOG_PHOTOALBUM);
                    dismiss();
                    break;
                case R.id.linearlayout_dialog_photoselect_background:
                    dismiss();
                    break;
                default:
                    break;
            }
        }
    };

}
