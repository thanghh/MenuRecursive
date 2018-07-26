package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class ParentMenuChildExpand implements Parent<ItemMenuChildExpand> {

    private String mName;
    private List<ItemMenuChildExpand> mIngredients;
    private boolean group;

    public ParentMenuChildExpand(String name, List<ItemMenuChildExpand> ingredients) {
        mName = name;
        mIngredients = ingredients;
        this.group = group;

    }

    public String getName() {
        return mName;
    }

    @Override
    public List<ItemMenuChildExpand> getChildList() {
        return mIngredients;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public ItemMenuChildExpand getIngredient(int position) {
        return mIngredients.get(position);
    }

    public boolean isVegetarian() {
        for (ItemMenuChildExpand ingredient : mIngredients) {
            if (!ingredient.isVegetarian()) {
                return false;
            }
        }
        return true;
    }
}
