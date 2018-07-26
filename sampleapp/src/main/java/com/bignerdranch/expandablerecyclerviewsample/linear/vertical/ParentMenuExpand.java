package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class ParentMenuExpand implements Parent<ItemMenuExpand> {

    private String mName;
    private List<ItemMenuExpand> mIngredients;
    private ItemMenuExpand Ingredients;
    private boolean mGroup;
    private ParentMenuChildExpand recipeChild;
    private ParentChildMenuAdapter mAdapter;

    public ParentMenuExpand(String name, List<ItemMenuExpand> ingredients) {
        mName = name;
        mIngredients = ingredients;

    }

    public ParentMenuExpand(String name, ItemMenuExpand ingredients) {
        mName = name;
        Ingredients = ingredients;

    }

    public String getName() {
        return mName;
    }

    @Override
    public List<ItemMenuExpand> getChildList() {
        return mIngredients;
    }



    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public ItemMenuExpand getIngredient(int position) {
        return mIngredients.get(position);
    }

    public boolean isVegetarian() {
        for (ItemMenuExpand ingredient : mIngredients) {
            if (!ingredient.isVegetarian()) {
                return false;
            }
        }
        return true;
    }


    public boolean isGroup() {
        for (ItemMenuExpand ingredient : mIngredients) {
            if (!ingredient.ismGroup()) {
                return false;
            }
        }
        return true;
    }

}
