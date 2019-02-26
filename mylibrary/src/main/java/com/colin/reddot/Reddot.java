package com.colin.reddot;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by huangchuangliang on 2019/2/22.
 */
public class Reddot {


    private String mKey;
    /**
     * 红点数值
     */
    private int mValue;

    /**
     * 父节点
     */
    private List<Reddot> mParents = new LinkedList<>();

    /**
     * 子红点列表
     */
    private List<Reddot> mChildList = new ArrayList<>();


    private List<ReddotLinsenter> mLinsenters = new ArrayList<>();


    /**
     * @param key 唯一标识
     */
    public Reddot(@NonNull String key) {
        ReddotManager.init();
        if (TextUtils.isEmpty(key)) {
            throw new RuntimeException("key is not empty");
        }
        mKey = key;
        ReddotManager.putReddot(this);
    }

    /**
     * @param key    唯一标识
     * @param parent 父节点
     */
    public Reddot(@NonNull String key, @NonNull Reddot parent) {
        this(key);
        addParent(parent);
    }

    public String getKey() {
        return mKey;
    }
    private void cauReddot() {
        mValue = 0;
        for (Reddot reddot : mChildList) {
            mValue += reddot.mValue;
        }
        for (Reddot pReddot : mParents) {
            pReddot.cauReddot();
        }

        //通知更新UI
        notifyLisenter();
    }

    private void notifyLisenter() {
        for (ReddotLinsenter linsenter : mLinsenters) {
            if (linsenter != null) {
                linsenter.updateView(mKey, mValue);
            }
        }
    }


    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
        for (Reddot pReddot : mParents) {
            pReddot.cauReddot();
        }
        notifyLisenter();
    }

    public void registerLisenter(@NonNull ReddotLinsenter linsenter) {
        if (!mLinsenters.contains(linsenter)) {
            mLinsenters.add(linsenter);
        }
        linsenter.updateView(mKey, mValue);
    }


    public void unRegisterLisenter(@NonNull ReddotLinsenter linsenter) {
        if (mLinsenters.contains(linsenter)) {
            mLinsenters.remove(linsenter);
        }
    }

    public void clearLisenterList() {
        mLinsenters.clear();
    }


    /**
     * @param parents
     */
    public void addParent(@NonNull Reddot... parents) {

        for (Reddot parent : parents) {
            //不能是自己
            if (parent == this) {
                throw new RuntimeException("parent no itself");
            }
            //不在子列表里面，以免产生循环
            if (isInChildlist(parent)) {
                throw new RuntimeException("parent is in mChildList");
            }

            if (!mParents.contains(parent)) {
                mParents.add(parent);
            }

            if (!parent.mChildList.contains(this)) {
                parent.mChildList.add(this);
            }
        }
    }

    public void clearParent() {

    }


    /**
     * 递归查询子列表
     *
     * @param parent
     * @return
     */
    private boolean isInChildlist(@NonNull Reddot parent) {
        if (mChildList.contains(parent)) {
            return true;
        }
        for (Reddot child : mChildList) {
            if (child.isInChildlist(parent)) {
                return true;
            }
        }
        return false;
    }

    public interface ReddotLinsenter {
        void updateView(String key, int count);
    }

    public void addOne() {
        setValue(mValue + 1);
    }

    public void reduceOne() {
        if (mValue > 0) {
            setValue(mValue - 1);
        }
    }


}
