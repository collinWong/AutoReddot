package com.colin.reddot;

/**
 * 存放红点的类
 * Created by huangchuangliang on 2019/2/25.
 */
public class ReddotList {
    public static Reddot mainReddot = new Reddot("mainReddot");
    public static Reddot secondReedot1 = new Reddot("secondReedot1",mainReddot);
    public static  Reddot secondReedot2 = new Reddot("secondReedot2",mainReddot);

    public static void init(){
        secondReedot1.addOne();
        secondReedot2.addOne();
    }
}
