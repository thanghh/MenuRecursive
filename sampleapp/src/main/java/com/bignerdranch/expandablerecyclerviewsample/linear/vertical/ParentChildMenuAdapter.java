package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerviewsample.R;

import java.util.List;

public class ParentChildMenuAdapter extends ExpandableRecyclerAdapter<ParentMenuChildExpand, ItemMenuChildExpand, ParentChildMenuViewHolder, ItemMenuChildViewHolder> {

    private static final int PARENT_VEGETARIAN = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int CHILD_VEGETARIAN = 2;
    private static final int CHILD_NORMAL = 3;

    private LayoutInflater mInflater;
    private List<ParentMenuChildExpand> mRecipeList;

    public ParentChildMenuAdapter(Context context, @NonNull List<ParentMenuChildExpand> recipeList) {
        super(recipeList);
        mRecipeList = recipeList;
        mInflater = LayoutInflater.from(context);
    }

    @UiThread
    @NonNull
    @Override
    public ParentChildMenuViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView;
        switch (viewType) {
            default:
            case PARENT_NORMAL:
                recipeView = mInflater.inflate(R.layout.recipe_view, parentViewGroup, false);
                break;
            case PARENT_VEGETARIAN:
                recipeView = mInflater.inflate(R.layout.vegetarian_recipe_view, parentViewGroup, false);
                break;
        }
        return new ParentChildMenuViewHolder(recipeView);
    }

    @UiThread
    @NonNull
    @Override
    public ItemMenuChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView;
        switch (viewType) {
            default:
            case CHILD_NORMAL:
                ingredientView = mInflater.inflate(R.layout.ingredient_view, childViewGroup, false);
                break;
            case CHILD_VEGETARIAN:
                ingredientView = mInflater.inflate(R.layout.vegetarian_ingredient_view, childViewGroup, false);
                break;
        }
        return new ItemMenuChildViewHolder(ingredientView);
    }


    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull ParentChildMenuViewHolder recipeViewHolder, int parentPosition, @NonNull ParentMenuChildExpand recipe) {
        recipeViewHolder.bind(recipe);
    }

    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ItemMenuChildViewHolder ingredientViewHolder, int parentPosition, int childPosition, @NonNull ItemMenuChildExpand ingredient) {
        ingredientViewHolder.bind(ingredient);
    }

    @Override
    public int getParentViewType(int parentPosition) {
        if (mRecipeList.get(parentPosition).isVegetarian()) {
            return PARENT_VEGETARIAN;
        } else {
            return PARENT_NORMAL;
        }
    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        ItemMenuChildExpand ingredient = mRecipeList.get(parentPosition).getIngredient(childPosition);
        if (ingredient.isVegetarian()) {
            return CHILD_VEGETARIAN;
        } else {
            return CHILD_NORMAL;
        }
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_VEGETARIAN || viewType == PARENT_NORMAL;
    }

}
