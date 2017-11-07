package com.smim.smimandroid.http;

import android.app.Activity;
import android.os.Handler;

import com.smim.smimandroid.Const;

import java.util.HashMap;

/**
 * Created by kakoapps on 2016-10-25.
 */

public class WebPost implements Const {

    Handler mHandler;
    Activity mActivity;
    HashMap<String, Object> mParams;



    /**
     * getVersion : 앱 버전관리
     * chk_id : 아이디 중복 체크
     * login : 로그인
     * member_reg : 회원가입
     * getAgreement : 이용약관
     * getBoardList : 게시판 리스트
     * getBanner : 메인배너 리스트
     * getBoardCate : 게시판-카테고리
     * leave : 회원탈퇴
     * getPointList : 포인트 내역
     * getSearch : 게시물 검색
     * setRefund : 환급신청
     * getRefund : 환급내역
     * getMyList : 내가 작성한 글
     * getMyComment : 내가 작성한 댓글
     * setAlarm : 알림설정
     * getMyPoint :  내 포인트 조회
     */
    public enum PostType{
        getVersion, chk_id, login, member_reg, getAgreement, getBoardList, getBanner, getBoardCate, leave, getPointList, getSearch, setRefund, getRefund, getMyList, getMyComment, setAlarm, getMyPoint;
    }

    public WebPost(Activity activity){
        this.mActivity = activity;
    }

    /**
     * @Method Name : addHashMap
     * @Method description : 해쉬맵 데이터 추가
     * @작성일 : 2016. 10. 25.
     * @작성자 : Park Jong Hoon
     * @param params
     */
    public WebPost addHashMap(HashMap<String, Object> params) {
        this.mParams = params;

        return this;
    }

    /**
     * @Method Name : addHandler
     * @Method description : 핸들러가 필요할시 추가
     * @작성일 : 2016. 10. 25.
     * @작성자 : Park Jong Hoon
     * @param mHandler
     */
    public void addHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    /**
     * @Method Name : getPostSend
     * @Method description : 레트로핏을 이용하여 http통신
     * @작성일 : 2016. 10. 25.
     * @작성자 : Park Jong Hoon
     * @param type
     */
    public WebPost getPostSend(final PostType type){
        switch (type){
            case getVersion:
                if(mParams != null){
//                    getVersion();
                }
                break;

        }
        return this;
    }



    /**
     * 앱 버전관리
     */
//    public void getVersion(){
//
//        final HttpsManager httpsManager = new HttpsManager(mActivity);
//        httpsManager.setService(UrlInfo.API_HEADER);
//
//        MyLogger.LogUrl(UrlInfo.HTTP_TAG, UrlInfo.GET_VERSION, mParams);
//        Utils.showLoadingDialog(mActivity, "", mActivity.getString(R.string.dialog_wait));
//        try{
//            Call<ReturnValueDTO> call = httpsManager.service.getHttpVersion(UrlInfo.GET_VERSION, mParams);
//            call.enqueue(new Callback<ReturnValueDTO>() {
//                @Override
//                public void onResponse(Call<ReturnValueDTO> call, Response<ReturnValueDTO> response) {
//                    Utils.hideLoadingDialog();
//
//                    ReturnValueDTO value = response.body();
//                    if (mHandler != null) {
//                        Message msg;
//                        msg = mHandler.obtainMessage(HTTP_SUCCESS, value);
//                        mHandler.sendMessage(msg);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ReturnValueDTO> call, Throwable t) {
//                    Utils.hideLoadingDialog();
//                    httpsManager.onError();
//                    if (mHandler != null) {
//                        Message msg;
//                        msg = mHandler.obtainMessage(HTTP_FAIL);
//                        mHandler.sendMessage(msg);
//                    }
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//            Utils.hideLoadingDialog();
//            httpsManager.onError();
//        }
//    }





    /**
     * 내 포인트 조회
     */
//    public void getMyPoint(){
//
//        final HttpsManager httpsManager = new HttpsManager(mActivity);
//        httpsManager.setService(UrlInfo.API_HEADER);
//
//        MyLogger.LogUrl(UrlInfo.HTTP_TAG, UrlInfo.GET_MYPOINT, mParams);
//        Utils.showLoadingDialog(mActivity, "", mActivity.getString(R.string.dialog_wait));
//        try{
//            Call<UserInfo> call = httpsManager.service.getHttpMyPoint(UrlInfo.GET_MYPOINT, mParams);
//            call.enqueue(new Callback<UserInfo>() {
//                @Override
//                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
//                    Utils.hideLoadingDialog();
//
//                    UserInfo value = response.body();
//                    if (mHandler != null) {
//                        Message msg;
//                        msg = mHandler.obtainMessage(HTTP_SUCCESS, value);
//                        mHandler.sendMessage(msg);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<UserInfo> call, Throwable t) {
//                    Utils.hideLoadingDialog();
//                    httpsManager.onError();
//                    if (mHandler != null) {
//                        Message msg;
//                        msg = mHandler.obtainMessage(HTTP_FAIL);
//                        mHandler.sendMessage(msg);
//                    }
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//            Utils.hideLoadingDialog();
//            httpsManager.onError();
//        }
//    }





}
