package com.smim.smimandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.smim.smimandroid.R;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by kakaoapps on 2015-12-08.
 */
public class Utils {


    // DP사이즈 구해오기
    public static int getDpSize(Context context, int dp) {
        int iDp = 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        iDp = (int) (dp * scale + 0.5f);
        return iDp;
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }


    public static boolean checkEmail(String email) {
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }


    // Get phone number
    public static String getPhoneNumber(Context context) {
        String phoneNumber;

        TelephonyManager tManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        phoneNumber = tManager.getLine1Number();
        if (phoneNumber != null) {
            if (phoneNumber.startsWith("+82")) {
                phoneNumber = phoneNumber.replace("+82", "0");
            }
            // String newNumber = phoneNumber;
            // if (phoneNumber.length() == 10)
            // {
            // newNumber = phoneNumber.substring(0, 3) + "-";
            // newNumber += phoneNumber.substring(3, 6) + "-";
            // newNumber += phoneNumber.substring(6, phoneNumber.length());
            // }
            // else if (phoneNumber.length() > 10)
            // {
            // newNumber = phoneNumber.substring(0, 3) + "-";
            // newNumber += phoneNumber.substring(3, 7) + "-";
            // newNumber += phoneNumber.substring(7, phoneNumber.length());
            // }
            // phoneNumber = newNumber;
        } else {
            phoneNumber = "";
        }

        return phoneNumber;
    }



    public static String getPhoneNumberSetBar(String phone){
        String strPhone = "";

        try{
            if(phone == null){
                return "";
            }


            if(phone.length()>0 && phone.length() == 11){
                //"01011112222"
                String head = phone.substring(0,3);
                String mid = phone.substring(3,7);
                String end = phone.substring(7,11);
                strPhone = (head + "-" + mid + "-" + end);
            }else if(phone.length()>0 && phone.length() == 10){
                //"0112223333"
                String head = phone.substring(0,3);
                String mid = phone.substring(3,6);
                String end = phone.substring(6,10);
                strPhone = (head + "-" + mid + "-" + end);
            }else if(phone.length()>0 && phone.length() > 11){
                //안심번호
                String head = phone.substring(0,4);
                String mid = phone.substring(4,7);
                String end = phone.substring(7,phone.length());
                strPhone = (head + "-" + mid + "-" + end);
            }
        }catch (Exception e){
            e.printStackTrace();
            strPhone = phone;
        }

        return strPhone;
    }


    // Show progress dialog
//    private static ProgressDialog loadingDialog = null;
    private static MyProgressDialog loadingDialog = null;

    public static final void showLoadingDialog(Context context, String title,
                                               String msg) {
        if (loadingDialog != null)
            hideLoadingDialog();

        loadingDialog = MyProgressDialog.show(context, "", "", true, true, null);
//        loadingDialog = ProgressDialog.show(context, title, msg, true, true, null);
//        loadingDialog = ProgressDialog.show(context, "", "", true, true, null);
//        if (!msg.equals(""))
//            return;
//
//        Window v = loadingDialog.getWindow();
//        WindowManager.LayoutParams param = v.getAttributes();
//        param.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
//        param.y = ((Activity) context).getWindowManager().getDefaultDisplay()
//                .getHeight() * 11 / 16;
//        param.dimAmount = 0.0f;
//        v.setAttributes(param);
    }

    /**
     * Progress Dialog Hide
     */
    public static final void hideLoadingDialog() {
        try {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 입력되어진 글자에 앞뒤로 ' 을 붙임 - 테이터베이스 필드 접근시 사용
    public static String AddQuote(String a_sData) {
        return "'" + a_sData + "'";
    }


    /**
     * @return
     * @brief 숫자 앞에 0으로 채우기
     */
    public static String getZeroFormat(int data) {
        String result = "";

        try {
            result = String.format("%02d", data);
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }

        return result;
    }

    //금액에 콤마찍기
    public static String changeAmt(String amt) {

        try{
            if (amt.length() == 0) {
                return "";
            }

            long value = Long.parseLong(amt);
            DecimalFormat format = new DecimalFormat("###,###");

            return format.format(value);
        }catch (Exception e){
            return "";
        }
    }

    //금액에 콤마찍기
    public static String changeAmt(int amt) {

        try{

            long value = amt;
            DecimalFormat format = new DecimalFormat("###,###");

            return format.format(value);
        }catch (Exception e){
            return "";
        }
    }



    public static String getURLEncode(String str) {
        String result = "";
        try {
            result = URLEncoder.encode(str, "UTF-8");
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }


    /**
     * 디바이스 정보 가져오기
     *
     * @param ctx
     * @param strOSVer
     * @param strModel
     * @param strId
     */
    public static void getDeviceInfo(Context ctx, String[] strOSVer, String[] strModel, String[] strId) {
        try {
            strOSVer[0] = Build.VERSION.RELEASE;
            strModel[0] = Build.MODEL;
            String serial = "empty";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                serial = Build.SERIAL;
            }

            TelephonyManager telManager = (TelephonyManager) ctx
                    .getSystemService(Context.TELEPHONY_SERVICE);

            Log.w("deviceid=", telManager.getDeviceId() + "");

            strId[0] = Build.MODEL + "_" + telManager.getDeviceId() + "_" + serial;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Map<String, Typeface> typefaceCache = new HashMap<String, Typeface>();

    public static void setTypeface(AttributeSet attrs, TextView textView) {
        if (textView.isInEditMode()) return;

        Context context = textView.getContext();

        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.AnyTextView);
        String typefaceName = values.getString(R.styleable.AnyTextView_typeface);

        if (typefaceCache.containsKey(typefaceName)) {
            textView.setTypeface(typefaceCache.get(typefaceName));
        } else {
            Typeface typeface;
            try {
                typeface = Typeface.createFromAsset(textView.getContext().getAssets(), typefaceName);
            } catch (Exception e) {
                Log.w(context.getString(R.string.app_name), String.format(context.getString(R.string.typeface_not_found), typefaceName));
                return;
            }

            typefaceCache.put(typefaceName, typeface);
            textView.setTypeface(typeface);
        }

        values.recycle();
    }


    public static String encryptSHA256(String str) {
        String SHA = "";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }


    /**
     * 현재 날짜 얻기
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }


    /**
     * 현재 날짜만 얻기
     *
     * @return
     */
    public static String getCurrentDateofDate() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }


    /**
     * 현재 날짜 얻기
     *
     * @return
     */
    public static String getCurrentDateBar() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }


    /**
     * 현재 날짜 얻기
     *
     * @return
     */
    public static String getCurrentDate0Time() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }

    /**
     * 현재 날짜 얻기
     *
     * @return
     */
    public static String getCurrentDate6Time() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 06:00:00", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }


    /**
     * 현재 날짜 얻기
     *
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }


    /**
     * 현재 날짜에 값 더하기
     */
    public static String getCurrentDateAddDate(int date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, date);

        // 특정 형태의 날짜로 값을 뽑기
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        String strDate = df.format(cal.getTime());
        return strDate;
    }

    /**
     * 원하는 날짜에 값 더하기
     */
    public static String getCurrentDateAddDate(String strdate, int date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateHappen = formatter.parse(strdate);
            Calendar calHappen = Calendar.getInstance();
            calHappen.setTime(dateHappen);
            calHappen.add(Calendar.DATE, date);

            // 특정 형태의 날짜로 값을 뽑기
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            String strDate = df.format(calHappen.getTime());
            return strDate;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 원하는 날짜에 값 더하기
     */
    public static String getCurrentDateAddDateDot(String strdate, int date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date dateHappen = formatter.parse(strdate);
            Calendar calHappen = Calendar.getInstance();
            calHappen.setTime(dateHappen);
            calHappen.add(Calendar.DATE, date);

            // 특정 형태의 날짜로 값을 뽑기
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            String strDate = df.format(calHappen.getTime());
            return strDate;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 현재 날짜에 값 더하기
     */
    public static String getCurrentDateAddDateBar(int date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, date);

        // 특정 형태의 날짜로 값을 뽑기
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = df.format(cal.getTime());
        return strDate;
    }

    /**
     * 현재 날짜에 값 더하기
     */
    public static String getCurrentDateAddDateType2(int date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, date);

        // 특정 형태의 날짜로 값을 뽑기
        DateFormat df = new SimpleDateFormat("MM월dd일");
        String strDate = df.format(cal.getTime());
        return strDate;
    }


    /**
     * 현재 년도
     *
     * @return
     */
    public static String getCurrentYear() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }


    /**
     * 현재 월
     *
     * @return
     */
    public static String getCurrentMonth() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }

    /**
     * 두 날짜의 차이구하기
     */
    public static long getElapsedDayofDayDot(String beforeday, String afterday){

        long lResult = -1;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            Date beginDate = formatter.parse(beforeday);
            Date endDate = formatter.parse(afterday);

            // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
            long diff = endDate.getTime() - beginDate.getTime();
            lResult = diff / (24 * 60 * 60 * 1000);

            System.out.println("날짜차이=" + lResult);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lResult;
    }

    /**
     * 두 날짜의 차이구하기
     */
    public static long getElapsedDayofDay(String beforeday, String afterday){

        long lResult = -1;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = formatter.parse(beforeday);
            Date endDate = formatter.parse(afterday);

            // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
            long diff = endDate.getTime() - beginDate.getTime();
            lResult = diff / (24 * 60 * 60 * 1000);

            System.out.println("날짜차이=" + lResult);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lResult;
    }

    public static long getElapsedTime(String compareTime) {
        long longElapsedMinTime = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            //발생시간 +1시간 계산
            Date dateHappen = formatter.parse(compareTime);

            Calendar calHappen = Calendar.getInstance();
            calHappen.setTime(dateHappen);
//            calHappen.add(Calendar.DATE, 1);

            String untilTime = formatter.format(calHappen.getTime());

            //현재시간
            String strNow = formatter.format(new Date());
            Date dateNow = formatter.parse(strNow);
            Calendar calNow = Calendar.getInstance();
            calNow.setTime(dateNow);


            //시간차이 계산
            longElapsedMinTime = ((calNow.getTimeInMillis() - calHappen.getTimeInMillis()) / 1000) / 60;
//			longElapsedMinTime = ((calHappen.getTimeInMillis() - calNow.getTimeInMillis()));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return longElapsedMinTime;
    }


    /**
     * 시간 비교하기
     *
     * @param currentTime
     * @param time        06:00:00 시간을 넣는다
     * @return
     */
    public static long getElapsedTime(String currentTime, String time) {
        long longElapsedMinTime = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd " + time, Locale.KOREA);
        Date compareTime = new Date();
        String mTime = mSimpleDateFormat.format(compareTime);


        try {
            //현재시간
            Date currentHappen = formatter.parse(currentTime);

            //비교 시간
            Date compareHappen = formatter.parse(mTime);

            Calendar calCurrentHappen = Calendar.getInstance();
            calCurrentHappen.setTime(currentHappen);
//            calHappen.add(Calendar.DATE, 1);

            String str_currentTime = formatter.format(calCurrentHappen.getTime());


            Calendar calCompareHappen = Calendar.getInstance();
            calCompareHappen.setTime(compareHappen);
//            calHappen.add(Calendar.DATE, 1);

            String str_compareTime = formatter.format(calCompareHappen.getTime());

            //현재시간
//            String strNow = formatter.format(new Date());
//            Date dateNow = formatter.parse(strNow);
//            Calendar calNow = Calendar.getInstance();
//            calNow.setTime(dateNow);


            //시간차이 계산
            longElapsedMinTime = ((calCurrentHappen.getTimeInMillis() - calCompareHappen.getTimeInMillis()) / 1000) / 60;
//			longElapsedMinTime = ((calHappen.getTimeInMillis() - calNow.getTimeInMillis()));

            MyLogger.LogE("TIMER", "발생시간 데이터 : " + longElapsedMinTime);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return longElapsedMinTime;
    }


    /**
     * 두개의 시간을 입력하여 두 시간의 차이를 HH:mm으로 보여준다.
     * @param beforetime
     * @param aftertime
     * @return
     */
    public static String getElapsedTimeInput(String beforetime, String aftertime){


        long longElapsedMinTime = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);


//        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
//        Date compareTime = new Date();
//        String mTime = mSimpleDateFormat.format(compareTime);



//        long longElapsedMinTime = 0;
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            //현재시간
            Date beforeHappen = formatter.parse(beforetime);

            //비교 시간
            Date afterHappen = formatter.parse(aftertime);


            Calendar calBeforeHappen = Calendar.getInstance();
            calBeforeHappen.setTime(beforeHappen);
            String str_beforeTime = formatter.format(calBeforeHappen.getTime());


            Calendar calAfterHappen = Calendar.getInstance();
            calAfterHappen.setTime(afterHappen);
            String str_AfterTime = formatter.format(calAfterHappen.getTime());


            MyLogger.LogE("before time : " +str_beforeTime + ", after time : " +str_AfterTime);


            //시간차이 계산
            longElapsedMinTime = ((calAfterHappen.getTimeInMillis() - calBeforeHappen.getTimeInMillis()) / 1000) / 60;

            MyLogger.LogE("TIMER", "발생시간 데이터 : " + longElapsedMinTime);

        } catch (Exception e) {
            e.printStackTrace();
        }

        int elapsedMinute = (int)longElapsedMinTime *60;
        int hour, minute;
        hour = (int)elapsedMinute/3600;
        minute = (int)elapsedMinute%3600/60;

        String dateString = hour+":"+minute;
//        try{
//            Date dateResult = formatter.parse(hour+":"+minute);
//            Calendar calResult = Calendar.getInstance();
//            calResult.setTime(dateResult);
//            dateString = formatter.format(calResult.getTime());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return dateString;
    }


    /**
     * 두개의 시간을 입력하여 두 시간의 차이를 분으로 보여준다.
     * @param beforetime
     * @param aftertime
     * @return
     */
    public static String getElapsedMinuteInput(String beforetime, String aftertime){


        long longElapsedMinTime = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);


//        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
//        Date compareTime = new Date();
//        String mTime = mSimpleDateFormat.format(compareTime);



//        long longElapsedMinTime = 0;
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            //현재시간
            Date beforeHappen = formatter.parse(beforetime);

            //비교 시간
            Date afterHappen = formatter.parse(aftertime);


            Calendar calBeforeHappen = Calendar.getInstance();
            calBeforeHappen.setTime(beforeHappen);
            String str_beforeTime = formatter.format(calBeforeHappen.getTime());


            Calendar calAfterHappen = Calendar.getInstance();
            calAfterHappen.setTime(afterHappen);
            String str_AfterTime = formatter.format(calAfterHappen.getTime());


            MyLogger.LogE("before time : " +str_beforeTime + ", after time : " +str_AfterTime);


            //시간차이 계산
            longElapsedMinTime = ((calAfterHappen.getTimeInMillis() - calBeforeHappen.getTimeInMillis()) / 1000) / 60;

            MyLogger.LogE("TIMER", "발생시간 데이터 : " + longElapsedMinTime);

        } catch (Exception e) {
            e.printStackTrace();
        }

        int elapsedMinute = (int)longElapsedMinTime;
//        int hour, minute;
//        hour = (int)elapsedMinute/3600;
//        minute = (int)elapsedMinute%3600/60;

        String dateString = elapsedMinute+"";
//        try{
//            Date dateResult = formatter.parse(hour+":"+minute);
//            Calendar calResult = Calendar.getInstance();
//            calResult.setTime(dateResult);
//            dateString = formatter.format(calResult.getTime());
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return dateString;
    }


    public static int getMinutesFromMillis(long milliseconds) {
        return (int) ((milliseconds / (1000 * 60)) % 60);
    }



    public static long getElapsedDate(String compareTime) {
        long longElapsedMinTime = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            //발생시간 +1시간 계산
            Date dateHappen = formatter.parse(compareTime);

            Calendar calHappen = Calendar.getInstance();
            calHappen.setTime(dateHappen);
//            calHappen.add(Calendar.DATE, 1);

            String untilTime = formatter.format(calHappen.getTime());

            //현재시간
            String strNow = formatter.format(new Date());
            Date dateNow = formatter.parse(strNow);
            Calendar calNow = Calendar.getInstance();
            calNow.setTime(dateNow);


            //시간차이 계산
            longElapsedMinTime = ((calNow.getTimeInMillis() - calHappen.getTimeInMillis()) / 1000) / 60;
//			longElapsedMinTime = ((calHappen.getTimeInMillis() - calNow.getTimeInMillis()));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return longElapsedMinTime;
    }


    public static long getAlarmElapsedTime(String date, String time) {

        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(date + " " + time, Locale.KOREA);
            SimpleDateFormat mSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            Date settingTime = new Date();
            String mTime = mSimpleDateFormat.format(settingTime);

            MyLogger.LogE("JHTEST", "mTime : " + mTime);


            //현재시간

            Date currentHappen = mSimpleDateFormat1.parse(mTime);
            Calendar calCurrentHappen = Calendar.getInstance();
            calCurrentHappen.setTime(currentHappen);


            String untilTime = mSimpleDateFormat1.format(currentHappen.getTime());
            MyLogger.LogE("JHTEST", "untilTime : " + untilTime);


            return calCurrentHappen.getTimeInMillis();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }


    /**
     * 패스워드 정규식
     */
    public static boolean getPasswordCheck(String input) {
        //문자, 숫자, 특수문자의 조합인지 확인
        Pattern p = Pattern.compile("([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])");
        Matcher m = p.matcher(input);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 숫자 정규식
     */
    public static boolean getNumberCheck(String input) {
        //문자, 숫자, 특수문자의 조합인지 확인
        Pattern p = Pattern.compile("(^[0-9]+$)");
        Matcher m = p.matcher(input);
        if (m.find()) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * 소수점 첫번째 자리까지 나타내기
     */
    public static float getFloatFirstPosition(float data) {
        float returnData = 0.0f;

        try {
            returnData = Math.round((data) * 10f) / 10f;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }

        return returnData;
    }


    /**
     * 소수점 첫번째 자리까지 나타내기
     */
    public static float getFloatFirstPosition(String data) {
        float returnData = 0.0f;

        try {
            returnData = Math.round((Float.parseFloat(data)) * 10f) / 10f;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }

        return returnData;
    }

    /**
     * Close keyboard
     */
    public static void hideKeyboard(Context ctx, EditText edit) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    }


    /**
     * 날짜 시간 폼 변경하기
     * @return
     */
    public static String changeDateTimeForm(String origindate, String changeform) {

        String changeData = "";

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );

        //발생시간 +1시간 계산
        try{
            Date dateHappen = mSimpleDateFormat.parse(origindate);

            SimpleDateFormat changeformat = new SimpleDateFormat( changeform, Locale.KOREA );
            changeData = changeformat.format(dateHappen);

        }catch (Exception e){
            e.printStackTrace();
        }

        return changeData;
    }


    /**
     * 날짜 폼 변경하기
     * @return
     */
    public static String changeDateForm(String origindate, String changeform) {

        String changeData = "";

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );

        //발생시간 +1시간 계산
        try{
            Date dateHappen = mSimpleDateFormat.parse(origindate);

            SimpleDateFormat changeformat = new SimpleDateFormat( changeform, Locale.KOREA );
            changeData = changeformat.format(dateHappen);

        }catch (Exception e){
            e.printStackTrace();
        }

        return changeData;
    }

    /**
     * 날짜 폼 변경하기
     * @return
     */
    public static String changeDateDotForm(String origindate, String changeform) {

        String changeData = "";

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyy.MM.dd", Locale.KOREA );

        //발생시간 +1시간 계산
        try{
            Date dateHappen = mSimpleDateFormat.parse(origindate);

            SimpleDateFormat changeformat = new SimpleDateFormat( changeform, Locale.KOREA );
            changeData = changeformat.format(dateHappen);

        }catch (Exception e){
            e.printStackTrace();
        }

        return changeData;
    }

    /**
     * 날짜 폼 변경하기
     * @return
     */
    public static String changeDateFormNoBar(String origindate, String changeform) {

        String changeData = "";

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "yyyyMMdd", Locale.KOREA );

        //발생시간 +1시간 계산
        try{
            Date dateHappen = mSimpleDateFormat.parse(origindate);

            SimpleDateFormat changeformat = new SimpleDateFormat( changeform, Locale.KOREA );
            changeData = changeformat.format(dateHappen);

        }catch (Exception e){
            e.printStackTrace();
        }

        return changeData;
    }


    /**
     * 시간 폼 변경하기
     * @return
     */
    public static String changeTimeForm(String origintime, String changeform) {

        String changeData = "";

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "HH:mm:ss", Locale.KOREA );

        //발생시간 +1시간 계산
        try{
            Date dateHappen = mSimpleDateFormat.parse(origintime);

            SimpleDateFormat changeformat = new SimpleDateFormat( changeform, Locale.KOREA );
            changeData = changeformat.format(dateHappen);

        }catch (Exception e){
            e.printStackTrace();
        }

        return changeData;
    }

    /**
     * 시간 요일 변경하기
     * @return
     */
    public static String changeDayofWeekTimeForm(String origintime, String changeform) {

        String changeData = "";

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat( "a hh:mm", Locale.KOREA );

        //발생시간 +1시간 계산
        try{
            Date dateHappen = mSimpleDateFormat.parse(origintime);

            SimpleDateFormat changeformat = new SimpleDateFormat( changeform, Locale.KOREA );
            changeData = changeformat.format(dateHappen);

        }catch (Exception e){
            e.printStackTrace();
        }

        return changeData;
    }



    /**
     * 버전별 컬러값 얻는 함수
     * @param context
     * @param color
     * @return
     */
    public static int getColorBuildVersion(Context context, int color){
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, color);
        } else {
            return context.getResources().getColor(color);
        }
    }

}
