package com.shenyu.laikaword.model.rxjava.rxbus.event;

/**
 * Created by Administrator on 2017/9/8 0008.
 * 事件类型
 */

public class EventType {
    /**
     * 打开左边菜单的页面
     */
    public static final int ACTION_OPONE_LEFT=0x01;
    /**
     * 上拉加载更多
     */
    public static final int ACTION_LODE_MORE=0x02;
    public static final int ACTION_PULL_REFRESH=0x03;
    public static final int ACTION_MAIN_SETDATE=0x04;
    //刷新用户数据
    public static final int ACTION_UPDATA_USER=0x05;
    //更新用户地址
    public static final int ACTION_UPDATA_USER_ADDRESS=0x06;
    //更新银行卡
    public static final int ACTION_UPDATA_USER_BANK=0x07;
    //更新银行卡
    public static final int ACTION_UPDATA_USER_REQUEST=0x08;
    public static final int ACTION_LFET_DATA=0x09;
    //微信支付
    public static final int ACTION_PAY_WEIXIN=0x10;

    public static final int CAR_PAGER_REFER=0x09;
    public static final int WEB_PAY=0x010;
    public static final int ACTION_ADDBANK=0x12;
}
