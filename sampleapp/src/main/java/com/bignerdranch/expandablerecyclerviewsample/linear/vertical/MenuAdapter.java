package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerviewsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ${CLASS}
 * Created by tryczson on 25/07/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_EXPAND = 0;
    private static final int ITEM_EXPAND_CHILD = 1;
    private static final int ITEM_CHILD = 2;

    private int level;
    private String name;
    private List<ParentMenuExpandnew> itemMenuExpands;

    private Context mContext;

    public MenuAdapter(Context context, int level) {
        this.level = level;
        this.mContext = context;
        this.itemMenuExpands = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {

            case ITEM_EXPAND:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recipe_view, parent, false);

                return new ParentMenuViewHolder(mContext, level + 1, itemMenuExpands, view);
            case ITEM_EXPAND_CHILD:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recipe_view_2, parent, false);

                return new ParentMenuViewHolder(mContext, level, itemMenuExpands, view);
            case ITEM_CHILD:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.ingredient_view, parent, false);

                return new ItemMenuViewHolder(mContext, view);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (itemMenuExpands.size() > 0 && itemMenuExpands.get(position).getmIngredients() != null && level == 0) {
            return ITEM_EXPAND;
        } else if (level == 1) {
            return ITEM_EXPAND_CHILD;
        } else {
            return ITEM_CHILD;
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ParentMenuViewHolder) {
            ((ParentMenuViewHolder) holder).bind(itemMenuExpands.get(position));
        }
        if (holder instanceof ItemMenuViewHolder) {
            ((ItemMenuViewHolder) holder).bind(itemMenuExpands.get(position));
        }
    }

    @Override
    public int getItemCount() {

        return itemMenuExpands.size();
    }

    public void addAll(List<ParentMenuExpandnew> expandnewList) {
        itemMenuExpands.addAll(expandnewList);
        notifyDataSetChanged();
    }

    public void clear() {
        itemMenuExpands.clear();
        notifyDataSetChanged();
    }
}
