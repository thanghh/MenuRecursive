package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerviewsample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ItemMenuViewHolder extends ItemMenuChildViewHolder {

    private TextView mIngredientTextView;

    private Context mContext;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private int count = 0;

    public ItemMenuViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        mContext = context;
        mIngredientTextView = (TextView) itemView.findViewById(R.id.ingredient_textview);

    }


    public void bind(@NonNull ParentMenuExpandnew ingredient) {
        mIngredientTextView.setText(ingredient.getName());
        Log.d(TAG, "bind: " + ingredient.getName());
//        if (ingredient.ismGroup()) {
//
//            ItemMenuExpand ketchup;
//            ItemMenuExpand bun;
//
//            ketchup = new ItemMenuExpand(mContext, ingredient.getName(), false);
//            bun = new ItemMenuExpand(mContext, ingredient.getName(), false);
//
//            if (count == 0) {
//                Log.d("add", "add: " + count);
//                ketchup.setmGroup(true);
//                count++;
//              bind(ingredient);
//
//            } else {
//                ketchup.setmGroup(false);
//            }
//
//            List<ItemMenuExpand> childList = new ArrayList<>();
//            childList.add(ketchup);
//            childList.add(bun);
//            ParentMenuExpand burger = new ParentMenuExpand("burger", childList);
//            final List<ParentMenuExpand> recipesChild = Arrays.asList(burger);
//            recipeChildAdapter = new ParentMenuAdapter(mContext, recipesChild);
//
//            recyclerView.setAdapter(recipeChildAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        } else {
//        }
    }


}
