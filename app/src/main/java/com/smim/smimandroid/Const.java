package com.smim.smimandroid;

import android.os.Environment;

import java.io.File;

/**
 * Created by kakoapps on 2016-01-27.
 */
public interface Const {

    final boolean isTest = false;

    final String DEVICE_ANDROID = "1";      //1:안드로이드, 2:아이폰

    final String AES_KEY  = "smimsapp_aeskey";

    //파일 저장 경로
    final String FILE_PATH = Environment.getExternalStorageDirectory()+ File.separator+"SMIM"+ File.separator;


    final String EMPTY_TIME = "0000-00-00 00:00:00";

    //리스트 가져오는 개수
    final int LIST_GET_COUNT = 10;
    final int LIST_DEFAULT_PAGE = 1;
    final int LIST_COUNT_PURCHASE = 12;
    final int LIST_COUNT_CHILDINFO = 12;
    final int LIST_COUNT_COMUNITY = 20;


    //카메라 앨범 선택 최대 개수
    final int CAMERA_ALBUM_MAXCOUNT = 5;
    final int CAMERA_ALBUM_PROFILE_MAXCOUNT = 1;
    final int CAMERA_ALBUM_CAR_MAXCOUNT = 3;


    //텍스트 사이즈
//    final int TEXTSIZE_LOW = 18;
//    final int TEXTSIZE_MID = 21;
//    final int TEXTSIZE_BIG = 25;
    final int TEXTSIZE_LOW = 21;
    final int TEXTSIZE_MID = 24;
    final int TEXTSIZE_BIG = 28;


    //Http통신 플레그 변수
    final int HTTP_SUCCESS = 0;
    final int HTTP_FAIL = 1;


    final int TYPE_SOUNDSELECT = 100;

    final String TYPE_SEX_MAN = "1";
    final String TYPE_SEX_WOMAN = "2";

    //오더 상태 변경
    final String TYPE_ORDERSTATE_ACCEPT = "1";      //수락
    final String TYPE_ORDERSTATE_COMPLETE = "2";    //완료
    final String TYPE_ORDERSTATE_CANCEL = "3";      //취소

    //배차판 플레그
    final int TYPE_STARTPOINT_STARTPOINT = 1;      //출발지
    final int TYPE_STARTPOINT_WAYPOINT1 = 3;      //경유지1
    final int TYPE_STARTPOINT_WAYPOINT2 = 4;      //경유지2
    final int TYPE_STARTPOINT_DESTINATION = 2;      //도착지


    //Sharedpreference 키 값
    final String KEY_LOGIN_FCMTOKEN = "fcm_token";
    final String KEY_PWD_VISIBLE = "pwd_visible";
    final String KEY_NEWVERSION = "newversion";






    final int TOPBAR_MAIN_TYPE = 100;          //메인페이지 탑바
    final int TOPBAR_MYPAGE_TYPE = 101;       //메뉴버튼에 텍스트 타이틀

    //프래그 먼트
    final int FRAGMENT_PURCHASE = 100;          //공동구매
    final int FRAGMENT_MAIN = 101;              //메인
    final int FRAGMENT_MYPAGE = 102;            //마이페이지
    final int FRAGMENT_REFUND_APPLICATION = 103;        //환급 신청
    final int FRAGMENT_REFUND_HISTORY = 104;            //환급 내역
    final int FRAGMENT_CHILDINFO = 105;         //육아정보
    final int FRAGMENT_GONGJI = 106;            //공지사항
    final int FRAGMENT_COMUNITY = 107;          //일반 커뮤니티



    //FRAGMENT TAG 키 값
    final String KEY_FRAGMENT_MAIN = "damoms_main";
    final String KEY_FRAGMENT_CATEGORY = "damoms_category";
    final String KEY_FRAGMENT_REFUND = "damoms_refund";


    //핸들러 플레그 값
    final int FLAG_DIALOG_CLOSE = 201;              //닫기 버튼
    final int FLAG_DIALOG_CONFIRM = 202;            //확인 버튼
    final int FLAG_INTRO_NORMAL = 203;              //일반 인트로
    final int FLAG_DIALOG_BACK = 204;               //백버튼
    final int FLAG_DIALOG_KAKAOSHARE = 205;               //카카오톡 공유
    final int FLAG_DIALOG_NAVERSHARE = 206;               //네이버 공유
    final int FLAG_DIALOG_KAKAOSTORYSHARE = 207;          //카카오스토리 공유
    final int FLAG_DIALOG_PHOTOTAKE = 208;          //사진 촬영
    final int FLAG_DIALOG_PHOTOALBUM = 209;         //앨범 선택
    final int FLAG_DIALOG_TWOCONFIRM = 210;         //버튼2 다이얼로그 확인 버튼


    //탭 플래그 값
    final int TAB_REFUND_APPLICATION = 100;
    final int TAB_REFUND_HISTORY = 101;
    final int TAB_MYACTIVITY_WRITEBOARD = 102;
    final int TAB_MYACTIVITY_COMMENTBOARD = 103;


    //인텐트 ForResult REQUEST 코드값
    final int REQUEST_USETERMS_USETERMS = 300;
    final int REQUEST_CAMERA = 301;
    final int REQUEST_AUTO_ORDERDRIVE = 302;
    final int REQUEST_PHONE_IDENTITY = 303;
    final int REQUEST_ACCOUNT_INFO = 304;
    final int REQUEST_ENABLE_BT = 305;
    final int REQUEST_CONNECT_DEVICE = 306;
    final int REQUEST_GONGJI_DETAIL = 307;


    final int PERMISSION_CAMERA_REQUST = 100;
    final int PERMISSION_ALBUM_REQUST = 101;
    final int PERMISSION_MIC_REQUST = 102;
    final int PERMISSION_PHONESTATE_REQUST = 103;
    final int PERMISSION_FINELOCATION_REQUEST = 104;
    final int PERMISSION_COARSELOCATION_REQUEST = 105;


    //인텐트 ForResult RESULT 코드값
    final int RESULT_BACK = 400;
    final int RESULT_REFRESH = 401;


    //PTYPE 푸쉬 타입 값
    final int PTYPE_SHAREDORDER = 1;        //공유오더 푸쉬
    final int PTYPE_AUTOORDER = 2;          //자동배차 푸쉬
    final int PTYPE_MSG = 3;                //메세지 푸시
    final int PTYPE_GONGJI = 4;             //공지 사항 푸시



    //번들 키값
    final String BUNDLE_KEY_USERINFO = "userinfo_key";
    final String BUNDLE_KEY_CODE = "codedata";


    final String BUNDLE_KEY_BOARDCODE = "board_code";
    final String BUNDLE_KEY_IDENTITY = "identity";
    final String BUNDLE_KEY_BANKCODE = "bankcode";
    final String BUNDLE_KEY_TEXTTYPE = "useterm_texttype";
    final String BUNDLE_KEY_SEARCH_KEYWORD = "search_keyword";





    //번들 Value
    final String BUNDLE_VALUE_MODIFY = "modify";
    final int BUNDLE_VALUE_JOIN = 100;
    final String BUNDLE_VALUE_USETERM_USEINFO = "use_info";
    final String BUNDLE_VALUE_USETERM_USERINFO = "pr_info";


    //카메라 촬영 플레그
    final int PICK_FROM_CAMERA = 500;       //카메라 촬영
    final int PICK_FROM_ALBUM = 501;        //카메라 앨범
    final int CROP_FROM_CAMERA = 502;       //카메라 크롭


    //마쉬멜로 권한 플레그
    final int AUTHORITY_CAMERA = 600;       //카메라
    final int AUTHORITY_ALBUM = 601;        //앨범
    final int AUTHORITY_MIC = 602;          //마이크
    final int AUTHORITY_PHONESTATE = 603;   //폰번호
    final int AUTHORITY_GPS = 604;          //GPS


    //이용약관 타입
//    final String TYPE_USETERMS_USETERM = "use_info";
//    final String TYPE_USETERMS_USERINFO = "pr_info";
//    final String TYPE_USETERMS_GPS = "gps_info";
//    final String TYPE_USETERMS_PRICE = "price_info";


    //다이얼로그 리스트 타입
    final String TYPE_AGE = "type_age";

}

