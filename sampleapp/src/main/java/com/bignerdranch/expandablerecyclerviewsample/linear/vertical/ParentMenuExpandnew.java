package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class ParentMenuExpandnew {

    private String mName;
    private List<ParentMenuExpandnew> mIngredients;
    private ItemMenuExpand Ingredients;
    private boolean mGroup;
    private ParentMenuChildExpand recipeChild;
    private ParentChildMenuAdapter mAdapter;

    public ParentMenuExpandnew(String name) {
        mName = name;

    }

    public ParentMenuExpandnew(String name, ItemMenuExpand ingredients) {
        mName = name;
        Ingredients = ingredients;

    }

    public List<ParentMenuExpandnew> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(List<ParentMenuExpandnew> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public String getName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ParentMenuExpandnew getIngredient(int position) {
        return mIngredients.get(position);
    }



//    public boolean isGroup() {
//        for (ParentMenuExpandnew ingredient : mIngredients) {
//            if (!ingredient.ismGroup()) {
//                return false;
//            }
//        }
//        return true;
//    }

}
