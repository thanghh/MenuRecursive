package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import android.content.Context;

public class ItemMenuExpand {

    private String mName;
    private boolean mIsVegetarian;
    private boolean mGroup;
    private Context mContext;
    ParentMenuExpand mTacoGroup;

    public ItemMenuExpand(Context context, boolean group) {

        mContext = context;
        mGroup = group;



    }

    public ItemMenuExpand(Context context, String name, boolean group) {
        mName = name;
        mGroup = group;
        mContext = context;

    }



    public String getNameGroup() {
        return mTacoGroup.getName();
    }

    public String getName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean isVegetarian() {
        return mIsVegetarian;
    }

    public boolean ismGroup() {
        return mGroup;
    }

    public void setmGroup(boolean mGroup) {
        this.mGroup = mGroup;
    }
}
