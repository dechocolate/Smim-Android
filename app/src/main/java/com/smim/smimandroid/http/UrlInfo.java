package com.smim.smimandroid.http;

public class UrlInfo {

	public static final String HTTP_TAG = "HTTP URL";
	private static final boolean SSL = false;

	public static final String RESULT_SUCCESS = "1";
	
	public static final String URL_HEADER = getHttp();
	
	private static final String getHttp() {
		if(SSL) return "https://";
		else return "http://";
	}

	//실서버
//	public static final String API_HEADER = "https://www.sure-chinese.com/api/";
	//테스트서버
	public static final String API_HEADER = "http://damoms.kakaoapps.co.kr/api/";


	public static final String IDENTITY_WEB = "http://14.49.39.33/api/?doc=member_phone_chk";

//	public static final String IDENTITY_WEB = "http://14.49.39.33/okname/hs_cnfrm_popup1.php";



	public static final String GET_VERSION = API_HEADER + "getVersion.php";						//앱 버전관리
	public static final String CHK_ID = API_HEADER + "chk_id.php";								//아이디 중복체크
	public static final String LOGIN = API_HEADER + "login.php";								//로그인
	public static final String MEMBER_REG = API_HEADER + "member_reg.php";						//회원가입
	public static final String GET_AGREEMENT = API_HEADER + "getAgreement.php";					//이용약관
	public static final String GET_BOARDLIST = API_HEADER + "getBoardList.php";					//게시판 리스트
	public static final String GET_BANNER = API_HEADER + "getBanner.php";						//배너 리스트
	public static final String GET_BOARDCATE = API_HEADER + "getBoardCate.php";					//게시판-카테고리
	public static final String LEAVE = API_HEADER + "leave.php";								//회원탈퇴
	public static final String MEMBER_EDIT = API_HEADER + "member_edit.php";					//회원정보 수정
	public static final String GET_SEARCH = API_HEADER + "getSearch.php";						//게시물 검색
	public static final String GET_POINTLIST = API_HEADER + "getPointList.php";					//포인트내역
	public static final String SET_REFUND = API_HEADER + "setRefund.php";						//환급신청
	public static final String GET_REFUND = API_HEADER + "getRefund.php";						//환급내역
	public static final String GET_MYLIST = API_HEADER + "getMyList.php";						//내가 작성한 글
	public static final String GET_MYCOMMENT = API_HEADER + "getMyComment.php";					//내가 작성한 댓글
	public static final String GET_ALARM = API_HEADER + "getAlarm.php";							//알림설정
	public static final String GET_MYPOINT = API_HEADER + "getMyPoint.php";						//내 포인트 조회








}
