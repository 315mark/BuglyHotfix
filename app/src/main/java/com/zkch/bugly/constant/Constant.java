package com.zkch.bugly.constant;

public class Constant {

    // 0 小燕 青年女声 中英文（普通话） xiaoyan
    // 1 默认 小宇 青年男声 中英文（普通话） xiaoyu
    // 2 凯瑟琳 青年女声 英文 catherine
    // 3 亨利 青年男声 英文 henry
    // 4 玛丽 青年女声 英文 vimary
    // 5 小研 青年女声 中英文（普通话） vixy
    // 6 小琪 青年女声 中英文（普通话） vixq xiaoqi
    // 7 小峰 青年男声 中英文（普通话） vixf
    // 8 小梅 青年女声 中英文（粤语） vixm xiaomei
    // 9 小莉 青年女声 中英文（台湾普通话） vixl xiaolin
    // 10 小蓉 青年女声 汉语（四川话） vixr xiaorong
    // 11 小芸 青年女声 汉语（东北话） vixyun xiaoqian
    // 12 小坤 青年男声 汉语（河南话） vixk xiaokun
    // 13 小强 青年男声 汉语（湖南话） vixqa xiaoqiang
    // 14 小莹 青年女声 汉语（陕西话） vixying
    // 15 小新 童年男声 汉语（普通话） vixx xiaoxin
    // 16 楠楠 童年女声 汉语（普通话） vinn nannan
    // 17 老孙 老年男声 汉语（普通话）

    // 发音人
    public final static String[] COLOUD_VOICERS_VALUE = {"xiaoyan", "xiaoyu", "catherine", "henry", "vimary", "vixy", "xiaoqi", "vixf", "xiaomei","xiaolin", "xiaorong", "xiaoqian", "xiaokun", "xiaoqiang", "vixying", "xiaoxin", "nannan", "vils",};

    public static final String WXPAY_URL="http://192.168.1.100:8080/pay/appPay";
    public static final String ALIPAY_URL="http://192.168.1.100:8080/alipay/appPay";
    public static final String UPPAY_URL="http://192.168.1.100:8080/unionpay/appConsume";


    private static final long TIMEOUT = 30;
}
