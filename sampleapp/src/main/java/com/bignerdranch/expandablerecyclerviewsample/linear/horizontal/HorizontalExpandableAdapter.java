package com.bignerdranch.expandablerecyclerviewsample.linear.horizontal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerviewsample.R;

import java.util.List;

/**
 * An example custom implementation of the ExpandableRecyclerAdapter.
 */
public class HorizontalExpandableAdapter extends ExpandableRecyclerAdapter<HorizontalParent,
        HorizontalChild, HorizontalParentViewHolder, HorizontalChildViewHolder> {

    private LayoutInflater mInflater;

    /**
     * Public primary constructor.
     *
     * @param parentItemList the list of parent items to be displayed in the RecyclerView
     */
    public HorizontalExpandableAdapter(Context context, @NonNull List<HorizontalParent> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    /**
     * OnCreateViewHolder implementation for parent items. The desired ParentViewHolder should
     * be inflated here
     *
     * @param parent for inflating the View
     * @return the user's custom parent ViewHolder that must extend ParentViewHolder
     */
    @UiThread
    @NonNull
    @Override
    public HorizontalParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_parent_horizontal, parent, false);
        return new HorizontalParentViewHolder(view);
    }

    /**
     * OnCreateViewHolder implementation for child items. The desired ChildViewHolder should
     * be inflated here
     *
     * @param parent for inflating the View
     * @return the user's custom parent ViewHolder that must extend ParentViewHolder
     */
    @UiThread
    @NonNull
    @Override
    public HorizontalChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_child_horizontal, parent, false);
        return new HorizontalChildViewHolder(view);
    }

    /**
     * OnBindViewHolder implementation for parent items. Any data or view modifications of the
     * parent view should be performed here.
     *
     * @param parentViewHolder the ViewHolder of the parent item created in OnCreateParentViewHolder
     * @param parentPosition the position in the RecyclerView of the item
     */
    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull HorizontalParentViewHolder parentViewHolder,
                                       int parentPosition, @NonNull HorizontalParent horizontalParent) {
        parentViewHolder.bind(horizontalParent.getParentNumber(), horizontalParent.getParentText());
    }

    /**
     * OnBindViewHolder implementation for child items. Any data or view modifications of the
     * child view should be performed here.
     *
     * @param childViewHolder the ViewHolder of the child item created in OnCreateChildViewHolder
     * @param childPosition the position in the RecyclerView of the item
     */
    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull HorizontalChildViewHolder childViewHolder, int parentPosition,
                                      int childPosition, @NonNull HorizontalChild horizontalChild) {
        childViewHolder.bind(horizontalChild.getChildText());
    }
}