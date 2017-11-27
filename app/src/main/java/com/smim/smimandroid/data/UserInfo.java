package com.smim.smimandroid.data;

import java.io.Serializable;

/**
 * Created by kakaoapps on 2016-01-04.
 */
public class UserInfo implements Serializable {

    public String status = "";
    public String msg = "";


    public String user_no = "";                 //회원 idx
    public String user_id = "";                 //회원아이디(카톡아이디)
    public String user_nick = "";               //회원 닉네임
    public String user_hp = "";                 //회원 휴대폰번호
    public String user_photo = "";              //회원 이미지
    public String user_point = "";              //회원 보유 포인트
    public String user_bank_nm = "";            //환급받을 은행명
    public String user_bank_num = "";            //환급받을 계좌번호
    public String user_bank_own = "";            //환급받을 예금주

    public String f_push_chk1 = "";             //전체알림
    public String f_push_chk2 = "";             //내댓글알림
    public String f_push_chk3 = "";             //댓글의댓글알림
    public String f_push_chk4 = "";             //다맘스 중요소식
    public String f_push_chk5 = "";             //마케팅





}
