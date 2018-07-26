package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bignerdranch.expandablerecyclerviewsample.R;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ParentMenuViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    @NonNull
    private final ImageView mArrowExpandImageView;
    private TextView mRecipeTextView;
    private MenuAdapter menuAdapter;
    private RecyclerView recyclerview_child;
    private LinearLayout layout_item;
    private List<ParentMenuExpandnew> itemMenuExpands;


    public ParentMenuViewHolder(Context context, int level, List<ParentMenuExpandnew> itemMenuExpands, @NonNull View itemView) {
        super(itemView);
        mRecipeTextView = (TextView) itemView.findViewById(R.id.recipe_textview);
        layout_item = (LinearLayout) itemView.findViewById(R.id.layout_item);
        this.itemMenuExpands = itemMenuExpands;

        mArrowExpandImageView = (ImageView) itemView.findViewById(R.id.arrow_expand_imageview);
        recyclerview_child = (RecyclerView) itemView.findViewById(R.id.recyclerview_child);
        menuAdapter = new MenuAdapter(context, level);

        recyclerview_child.setAdapter(menuAdapter);
        recyclerview_child.setLayoutManager(new LinearLayoutManager(context));
    }

    public void bind(@NonNull final ParentMenuExpandnew recipe) {

        mRecipeTextView.setText(recipe.getName());
        layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuAdapter.getItemCount() > 0) {
                    menuAdapter.clear();
                } else {
                    if (recipe.getmIngredients() != null) {
                        menuAdapter.addAll(recipe.getmIngredients());
                    }
                }
            }
        });

    }


}
