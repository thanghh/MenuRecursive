package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

public class ItemMenuChildExpand {

    private String mName;
    private boolean mIsVegetarian;
    private boolean mGroup;

    public ItemMenuChildExpand(String name, boolean isVegetarian) {
        mName = name;
        mIsVegetarian = isVegetarian;

    }

    public String getName() {
        return mName;
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
