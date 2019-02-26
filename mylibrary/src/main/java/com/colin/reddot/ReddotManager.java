package com.colin.reddot;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by huangchuangliang on 2019/2/22.
 */
public class ReddotManager {
    private static final String TAG = ReddotManager.class.getSimpleName();
    private static ReddotManager instance;

    private HashMap<String, Reddot> mMap = new HashMap();

    protected static void init() {
        if(instance  == null) {
            instance = new ReddotManager();
        }
    }


    public static void putReddot(Reddot reddot){
        if(instance != null){
            instance.mMap.put(reddot.getKey(),reddot);
        }
    }

    public static Reddot getReddot(String key){
        if(instance != null){
            return instance.mMap.get(key);
        }
        return null;
    }



    public static void registerLinsenter(String key, Reddot.ReddotLinsenter linsenter) {
        if (instance != null && key != null) {
            Reddot reddot = instance.mMap.get(key);
            if(reddot != null){
                reddot.registerLisenter(linsenter);
            }else{
                Log.e(TAG,"no this reddot");
            }
        }
    }



    /**
     * 移除掉当前监听器
     * @param key
     * @param linsenter
     */
    public static void unRegisterLinsenter(String key,Reddot.ReddotLinsenter linsenter) {
        if (instance != null && key != null) {
            Reddot reddot = instance.mMap.get(key);
            if(reddot != null){
                reddot.unRegisterLisenter(linsenter);
            }else{
                Log.e(TAG,"no this reddot");
            }
        }
    }


    /**
     * 清除该红点所有监听器
     * @param key
     */
    public static void clearLinsenter(String key) {
        if (instance != null && key != null) {
            Reddot reddot = instance.mMap.get(key);
            if(reddot != null){
                reddot.clearLisenterList();
            }else{
                Log.e(TAG,"no this reddot");
            }
        }
    }


    /**
     * 设置红点值
     * @param key
     * @param value
     */
    public static void setReddotValue(String key,int value){
        if(key !=null && value >=0 && instance != null){
            Reddot reddot = instance.mMap.get(key);
            if(reddot != null){
                reddot.setValue(value);
            }else{
                Log.e(TAG,"no this reddot");
            }
        }
    }


}
