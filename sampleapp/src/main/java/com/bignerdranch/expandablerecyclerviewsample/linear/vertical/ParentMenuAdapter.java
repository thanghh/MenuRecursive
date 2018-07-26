package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerviewsample.R;

import java.util.ArrayList;
import java.util.List;

public class ParentMenuAdapter extends ExpandableRecyclerAdapter<ParentMenuExpand, ItemMenuExpand, ParentMenuViewHolder, ItemMenuViewHolder> {

    private static final int PARENT_VEGETARIAN = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int PARENT_VEGETARIAN_GROUP = 2;
    private static final int CHILD_VEGETARIAN = 3;
    private static final int CHILD_NORMAL = 4;
    private static final int CHILD_GROUP = 5;

    private LayoutInflater mInflater;
    private List<ParentMenuExpand> mRecipeList;
    private List<ParentMenuExpand> mRecipeChildList = new ArrayList<>();
    private Context mContext;

    public ParentMenuAdapter(Context context,  @NonNull List<ParentMenuExpand> recipeList) {
        super(recipeList);
        mRecipeList = recipeList;
        mContext = context;
        mInflater = LayoutInflater.from(context);

    }

    @UiThread
    @NonNull
    @Override
    public ParentMenuViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView;
        switch (viewType) {
            default:
            case PARENT_NORMAL:
                recipeView = mInflater.inflate(R.layout.recipe_view, parentViewGroup, false);
                break;
//            case PARENT_VEGETARIAN:
//                recipeView = mInflater.inflate(R.layout.vegetarian_recipe_view, parentViewGroup, false);
//                break;

        }
        return null;
    }

    @UiThread
    @NonNull
    @Override
    public ItemMenuViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView;
        switch (viewType) {
            default:
            case CHILD_NORMAL:
                ingredientView = mInflater.inflate(R.layout.ingredient_view, childViewGroup, false);
                break;
            case CHILD_GROUP:
                ingredientView = mInflater.inflate(R.layout.activity_recycler_view_group, childViewGroup, false);
                break;


        }
        return null;
    }


    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull ParentMenuViewHolder recipeViewHolder, int parentPosition, @NonNull ParentMenuExpand recipe) {

    }

    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ItemMenuViewHolder ingredientViewHolder, int parentPosition, int childPosition, @NonNull ItemMenuExpand ingredient) {

    }

    @Override
    public int getParentViewType(int parentPosition) {
        if (mRecipeList.get(parentPosition).isGroup()) {
            return CHILD_GROUP;
        } else {
            return PARENT_NORMAL;
        }

    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        ItemMenuExpand ingredient = mRecipeList.get(parentPosition).getIngredient(childPosition);

        if(ingredient.ismGroup()){
            return CHILD_GROUP;
        } else {
            return CHILD_NORMAL;
        }


    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_VEGETARIAN || viewType == PARENT_NORMAL;
    }

}
