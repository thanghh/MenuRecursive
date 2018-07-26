package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerviewsample.R;

public class ItemMenuChildViewHolder extends ChildViewHolder {

    private TextView mIngredientTextView;
    public ItemMenuChildViewHolder(@NonNull View itemView) {
        super(itemView);
        mIngredientTextView = (TextView) itemView.findViewById(R.id.ingredient_textview);
    }

    public void bind(@NonNull ItemMenuChildExpand ingredient) {
        mIngredientTextView.setText(ingredient.getName());
    }
}
