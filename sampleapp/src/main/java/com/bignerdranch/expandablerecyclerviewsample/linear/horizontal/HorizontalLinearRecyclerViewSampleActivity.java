package com.bignerdranch.expandablerecyclerviewsample.linear.horizontal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerviewsample.R;

import java.util.ArrayList;
import java.util.List;

public class HorizontalLinearRecyclerViewSampleActivity extends AppCompatActivity implements ExpandableRecyclerAdapter.ExpandCollapseListener {

    private static final int NUM_TEST_DATA_ITEMS = 20;
    private static final int EXPAND_COLLAPSE_SINGLE_PARENT_INDEX = 2;
    private static final String SAVED_TEST_DATA_ITEM_LIST = "HorizontalLinearRecyclerViewSampleActivity.SavedTestDataItemList";

    private RecyclerView mRecyclerView;
    private Button mExpandParentTwoButton;
    private Button mCollapseParentTwoButton;
    private Button mExpandAllButton;
    private Button mCollapseAllButton;

    @Nullable
    private ArrayList<HorizontalParent> mTestDataItemList;

    @Nullable
    private HorizontalExpandableAdapter mExpandableAdapter;


    @NonNull
    public static Intent newIntent(Context context) {
        return new Intent(context, HorizontalLinearRecyclerViewSampleActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_linear_recycler_view_sample);

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_horizontal_linear_recycler_view_sample_recyclerView);

        Button expandParentTwoButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_expand_parent_two_button);
        expandParentTwoButton.setOnClickListener(mExpandParentTwoClickListener);

        Button collapseParentTwoButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_collapse_parent_two_button);
        collapseParentTwoButton.setOnClickListener(mCollapseParentTwoClickListener);

        Button expandAllButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_expand_all_button);
        expandAllButton.setOnClickListener(mExpandAllClickListener);

        Button collapseAllButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_collapse_all_button);
        collapseAllButton.setOnClickListener(mCollapseAllClickListener);

        Button addToEndButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_add_to_end_button);
        addToEndButton.setOnClickListener(mAddToEndClickListener);

        Button removeFromEndButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_remove_from_end_button);
        removeFromEndButton.setOnClickListener(mRemoveFromEndClickListener);

        Button addToSecondButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_add_to_second_button);
        addToSecondButton.setOnClickListener(mAddToSecondClickListener);

        Button removeSecondbutton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_remove_second_button);
        removeSecondbutton.setOnClickListener(mRemoveSecondClickListener);

        Button addChild = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_add_child);
        addChild.setOnClickListener(mAddChildClickListener);

        Button removeChild = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_remove_child);
        removeChild.setOnClickListener(mRemoveChildClickListener);

        Button addMultipleParents = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_add_multiple_parents);
        addMultipleParents.setOnClickListener(mAddMultipleParentsClickListener);

        Button modifyLastParent = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_modify_last_parent);
        modifyLastParent.setOnClickListener(mModifyLastParentClickListener);

        Button modifyLastChild = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_modify_last_child);
        modifyLastChild.setOnClickListener(mModifyLastChildClickListener);

        Button expandRangeButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_expand_range);
        expandRangeButton.setOnClickListener(mExpandRangeClickListener);

        Button collapseRangeButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_collapse_range);
        collapseRangeButton.setOnClickListener(mCollapseRangeClickListener);

        Button addTwoChildren = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_add_two_children);
        addTwoChildren.setOnClickListener(mAddTwoChildrenClickListener);

        Button removeTwoChildren = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_remove_two_children);
        removeTwoChildren.setOnClickListener(mRemoveTwoChildrenClickListener);

        Button modifyTwoChildren = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_modify_two_children);
        modifyTwoChildren.setOnClickListener(mModifyTwoChildrenClickListener);

        Button removeTwoParents = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_remove_two_parents);
        removeTwoParents.setOnClickListener(mRemoveTwoParentsClickListener);

        Button modifyTwoParents = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_modify_two_parents);
        modifyTwoParents.setOnClickListener(mModifyTwoParentsClickListener);

        Button parentMoveButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_parent_move);
        parentMoveButton.setOnClickListener(mParentMoveClickListener);

        Button childMoveButton = (Button) findViewById(R.id.activity_horizontal_linear_recycler_view_child_move);
        childMoveButton.setOnClickListener(mChildMoveClickListener);

        // Create a new adapter with 20 test data items
        if (savedInstanceState == null) {
            mTestDataItemList = setUpTestData(NUM_TEST_DATA_ITEMS);
        } else {
            mTestDataItemList = (ArrayList<HorizontalParent>) savedInstanceState.getSerializable(SAVED_TEST_DATA_ITEM_LIST);
        }
        mExpandableAdapter = new HorizontalExpandableAdapter(this, mTestDataItemList);

        // Attach this activity to the Adapter as the ExpandCollapseListener
        mExpandableAdapter.setExpandCollapseListener(this);

        // Set the RecyclerView's adapter to the ExpandableAdapter we just created
        mRecyclerView.setAdapter(mExpandableAdapter);
        // Set the layout manager to a LinearLayout manager for vertical list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    /**
     * Save the instance state of the adapter to keep expanded/collapsed states when rotating or
     * pausing the activity.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mExpandableAdapter.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_TEST_DATA_ITEM_LIST, mTestDataItemList);
    }

    /**
     * Load the expanded/collapsed states of the adapter back into the view when done rotating or
     * resuming the activity.
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mExpandableAdapter.onRestoreInstanceState(savedInstanceState);
    }

    @UiThread
    @Override
    public void onParentExpanded(int parentPosition) {
        String toastMessage = getString(R.string.item_expanded, parentPosition);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    @Override
    public void onParentCollapsed(int parentPosition) {
        String toastMessage = getString(R.string.item_collapsed, parentPosition);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private View.OnClickListener mExpandParentTwoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mExpandableAdapter.expandParent(EXPAND_COLLAPSE_SINGLE_PARENT_INDEX);
        }
    };

    @NonNull
    private View.OnClickListener mCollapseParentTwoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mExpandableAdapter.collapseParent(EXPAND_COLLAPSE_SINGLE_PARENT_INDEX);
        }
    };

    @NonNull
    private View.OnClickListener mExpandAllClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mExpandableAdapter.expandAllParents();
        }
    };

    @NonNull
    private View.OnClickListener mCollapseAllClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mExpandableAdapter.collapseAllParents();
        }
    };

    @NonNull
    private OnClickListener mAddToEndClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            ArrayList<HorizontalChild> childList = new ArrayList<>();
            int parentNumber = mTestDataItemList.size();

            HorizontalChild horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.child_text, parentNumber));
            childList.add(horizontalChild);

            horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.second_child_text, parentNumber));
            childList.add(horizontalChild);

            horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.third_child_text, parentNumber));
            childList.add(horizontalChild);

            HorizontalParent horizontalParent = new HorizontalParent();
            horizontalParent.setChildItemList(childList);
            horizontalParent.setParentNumber(parentNumber);
            horizontalParent.setParentText(getString(R.string.parent_text, parentNumber));
            if (parentNumber % 2 == 0) {
                horizontalParent.setInitiallyExpanded(true);
            }


            mTestDataItemList.add(horizontalParent);
            mExpandableAdapter.notifyParentInserted(mTestDataItemList.size() - 1);
        }
    };

    @NonNull
    private OnClickListener mAddToSecondClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ArrayList<HorizontalChild> childList = new ArrayList<>();
            int parentNumber = mTestDataItemList.size();

            HorizontalChild horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.child_insert_text, 1));
            childList.add(horizontalChild);

            horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.child_insert_text, 2));
            childList.add(horizontalChild);

            HorizontalParent horizontalParent = new HorizontalParent();
            horizontalParent.setChildItemList(childList);
            horizontalParent.setParentNumber(parentNumber);
            horizontalParent.setParentText(getString(R.string.inserted_parent_text));
            if (parentNumber % 2 == 0) {
                horizontalParent.setInitiallyExpanded(true);
            }

            mTestDataItemList.add(1, horizontalParent);
            mExpandableAdapter.notifyParentInserted(1);
        }
    };

    @NonNull
    private OnClickListener mRemoveSecondClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTestDataItemList.size() < 2) {
                return;
            }

            HorizontalParent horizontalParent = mTestDataItemList.get(1);
            mTestDataItemList.remove(horizontalParent);
            mExpandableAdapter.notifyParentRemoved(1);

        }
    };

    @NonNull
    private OnClickListener mRemoveFromEndClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int removeIndex = mTestDataItemList.size() - 1;
            mTestDataItemList.remove(removeIndex);
            mExpandableAdapter.notifyParentRemoved(removeIndex);
        }
    };

    @NonNull
    private OnClickListener mAddChildClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTestDataItemList.size() < 2) {
                return;
            }

            HorizontalParent horizontalParent = mTestDataItemList.get(1);
            HorizontalChild horizontalChild = new HorizontalChild();
            List<HorizontalChild> childList = horizontalParent.getChildList();
            horizontalChild.setChildText(getString(R.string.added_child, childList.size()));
            childList.add(horizontalChild);
            mExpandableAdapter.notifyChildInserted(1, childList.size() - 1);
        }
    };

    @NonNull
    private OnClickListener mRemoveChildClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTestDataItemList.size() < 2) {
                return;
            }

            HorizontalParent horizontalParent = mTestDataItemList.get(1);
            List<HorizontalChild> childList = horizontalParent.getChildList();
            if (childList.size() < 2) {
                return;
            }

            childList.remove(1);
            mExpandableAdapter.notifyChildRemoved(1, 1);
        }
    };

    @NonNull
    private OnClickListener mAddMultipleParentsClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // Adds first parent
            ArrayList<HorizontalChild> childList = new ArrayList<>();
            int parentNumber = mTestDataItemList.size();

            HorizontalChild horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.child_insert_text, 1));
            childList.add(horizontalChild);

            horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.child_insert_text, 2));
            childList.add(horizontalChild);

            HorizontalParent horizontalParent = new HorizontalParent();
            horizontalParent.setChildItemList(childList);
            horizontalParent.setParentNumber(parentNumber);
            horizontalParent.setParentText(getString(R.string.inserted_parent_text));
            if (parentNumber % 2 == 0) {
                horizontalParent.setInitiallyExpanded(true);
            }


            mTestDataItemList.add(1, horizontalParent);

            childList = new ArrayList<>();
            parentNumber = mTestDataItemList.size();

            horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.child_insert_text, 1));
            childList.add(horizontalChild);

            horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.child_insert_text, 2));
            childList.add(horizontalChild);

            horizontalParent = new HorizontalParent();
            horizontalParent.setChildItemList(childList);
            horizontalParent.setParentNumber(parentNumber);
            horizontalParent.setParentText(getString(R.string.inserted_parent_text));
            if (parentNumber % 2 == 0) {
                horizontalParent.setInitiallyExpanded(true);
            }

            mTestDataItemList.add(2, horizontalParent);

            mExpandableAdapter.notifyParentRangeInserted(1, 2);
        }
    };

    @NonNull
    private OnClickListener mModifyLastParentClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int parentNumber = mTestDataItemList.size() - 1;
            HorizontalParent horizontalParent = mTestDataItemList.get(parentNumber);
            HorizontalParent newHorizontalParent = new HorizontalParent();
            newHorizontalParent.setParentText(getString(R.string.modified_parent_text, parentNumber));
            newHorizontalParent.setParentNumber(parentNumber);
            int childSize = horizontalParent.getChildList().size();
            List<HorizontalChild> childItemList = new ArrayList<>();
            for (int i = 0; i < childSize; i++) {
                HorizontalChild horizontalChild = new HorizontalChild();
                horizontalChild.setChildText(getString(R.string.modified_child_text, i));
                childItemList.add(horizontalChild);
            }
            newHorizontalParent.setChildItemList(childItemList);
            mTestDataItemList.set(parentNumber, newHorizontalParent);
            mExpandableAdapter.notifyParentChanged(parentNumber);
        }
    };

    @NonNull
    private OnClickListener mModifyLastChildClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int parentNumber = mTestDataItemList.size() - 1;
            HorizontalParent horizontalParent = mTestDataItemList.get(parentNumber);
            List<HorizontalChild> childList = horizontalParent.getChildList();
            int childNumber = horizontalParent.getChildList().size() - 1;
            HorizontalChild horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.modified_child_text, childNumber));
            childList.set(childNumber, horizontalChild);
            mExpandableAdapter.notifyChildChanged(parentNumber, childNumber);
        }
    };

    @NonNull
    private OnClickListener mExpandRangeClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            mExpandableAdapter.expandParentRange(4, 3);
        }
    };

    @NonNull
    private OnClickListener mCollapseRangeClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            mExpandableAdapter.collapseParentRange(2, 4);
        }
    };

    @NonNull
    private OnClickListener mAddTwoChildrenClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            HorizontalParent horizontalParent = mTestDataItemList.get(mTestDataItemList.size() - 1);
            List<HorizontalChild> childList = horizontalParent.getChildList();

            HorizontalChild horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.added_child, childList.size()));
            childList.add(horizontalChild);

            horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.added_child, childList.size()));
            childList.add(horizontalChild);
            mExpandableAdapter.notifyChildRangeInserted(mTestDataItemList.size() - 1, childList.size() - 2, 2);
        }
    };

    @NonNull
    private OnClickListener mRemoveTwoChildrenClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            HorizontalParent horizontalParent = mTestDataItemList.get(mTestDataItemList.size() - 1);
            List<HorizontalChild> childList = horizontalParent.getChildList();
            int childSize = childList.size();
            if (childSize < 1) {
                return;
            }

            childList.remove(childSize - 1);
            if (childSize < 2) {
                mExpandableAdapter.notifyChildRemoved(mTestDataItemList.size() - 1, childSize - 1);
                return;
            }
            childList.remove(childSize - 2);
            mExpandableAdapter.notifyChildRangeRemoved(mTestDataItemList.size() - 1, childSize - 2, 2);
        }
    };

    @NonNull
    private OnClickListener mModifyTwoChildrenClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTestDataItemList.size() < 1) {
                return;
            }

            int parentNumber = mTestDataItemList.size() - 1;
            HorizontalParent horizontalParent = mTestDataItemList.get(parentNumber);
            List<HorizontalChild> childList = horizontalParent.getChildList();
            if (childList.size() == 0) {
                return;
            }

            int childNumber = horizontalParent.getChildList().size() - 1;
            HorizontalChild horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.modified_child_text, childNumber));
            childList.set(childNumber, horizontalChild);

            if (childList.size() == 1) {
                mExpandableAdapter.notifyChildChanged(parentNumber, childNumber);
                return;
            }

            childNumber = horizontalParent.getChildList().size() - 2;
            horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.modified_child_text, childNumber));
            childList.set(childNumber, horizontalChild);

            mExpandableAdapter.notifyChildRangeChanged(parentNumber, childNumber, 2);
        }
    };

    @NonNull
    private OnClickListener mRemoveTwoParentsClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTestDataItemList.size() < 1) {
                return;
            }

            mTestDataItemList.remove(mTestDataItemList.size() - 1);
            if (mTestDataItemList.size() < 1) {
                mExpandableAdapter.notifyParentRemoved(mTestDataItemList.size());
                return;
            }
            mTestDataItemList.remove(mTestDataItemList.size() - 1);

            mExpandableAdapter.notifyParentRangeRemoved(mTestDataItemList.size(), 2);
        }
    };

    @NonNull
    private OnClickListener mModifyTwoParentsClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTestDataItemList.size() < 1) {
                return;
            }

            int parentNumber = mTestDataItemList.size() - 1;
            HorizontalParent horizontalParent = mTestDataItemList.get(parentNumber);
            HorizontalParent newHorizontalParent = new HorizontalParent();
            newHorizontalParent.setParentText(getString(R.string.modified_parent_text, parentNumber));
            newHorizontalParent.setParentNumber(parentNumber);
            int childSize = horizontalParent.getChildList().size();
            List<HorizontalChild> childItemList = new ArrayList<>();
            for (int i = 0; i < childSize; i++) {
                HorizontalChild horizontalChild = new HorizontalChild();
                horizontalChild.setChildText(getString(R.string.modified_child_text, i));
                childItemList.add(horizontalChild);
            }
            newHorizontalParent.setChildItemList(childItemList);
            mTestDataItemList.set(parentNumber, newHorizontalParent);
            if (mTestDataItemList.size() < 2) {
                mExpandableAdapter.notifyParentChanged(parentNumber);
                return;
            }


            parentNumber = mTestDataItemList.size() - 2;
            horizontalParent = mTestDataItemList.get(parentNumber);
            newHorizontalParent = new HorizontalParent();
            newHorizontalParent.setParentText(getString(R.string.modified_parent_text, parentNumber));
            newHorizontalParent.setParentNumber(parentNumber);
            childSize = horizontalParent.getChildList().size();
            childItemList = new ArrayList<>();
            for (int i = 0; i < childSize; i++) {
                HorizontalChild horizontalChild = new HorizontalChild();
                horizontalChild.setChildText(getString(R.string.modified_child_text, i));
                childItemList.add(horizontalChild);
            }
            newHorizontalParent.setChildItemList(childItemList);
            mTestDataItemList.set(parentNumber, newHorizontalParent);
            mExpandableAdapter.notifyParentRangeChanged(parentNumber, 2);
        }
    };

    @NonNull
    private OnClickListener mParentMoveClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTestDataItemList.size() < 4) {
                return;
            }

            HorizontalParent horizontalParent = mTestDataItemList.remove(1);
            mTestDataItemList.add(3, horizontalParent);
            mExpandableAdapter.notifyParentMoved(1, 3);
        }
    };

    @NonNull
    private OnClickListener mChildMoveClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTestDataItemList.size() < 2) {
                return;
            }

            HorizontalParent horizontalParent = mTestDataItemList.get(1);
            if (horizontalParent.getChildList().size() < 2) {
                return;
            }
            List<HorizontalChild> childList = horizontalParent.getChildList();
            HorizontalChild child = childList.remove(0);
            childList.add(childList.size(), child);
            mExpandableAdapter.notifyChildMoved(1, 0, childList.size() - 1);

        }
    };


    /**
     * Method to set up test data used in the RecyclerView.
     *
     * Each child list item contains a string.
     * Each parent list item contains a number corresponding to the number of the parent and a string
     * that contains a message.
     * Each parent also contains a list of children which is generated in this. Every odd numbered
     * parent gets one child and every even numbered parent gets two children.
     *
     * @return A List of Objects that contains all parent items. Expansion of children are handled in the adapter
     */
    @NonNull
    private ArrayList<HorizontalParent> setUpTestData(int numItems) {
        ArrayList<HorizontalParent> horizontalParentList = new ArrayList<>();

        for (int i = 0; i < numItems; i++) {
            List<HorizontalChild> childItemList = new ArrayList<>();

            HorizontalChild horizontalChild = new HorizontalChild();
            horizontalChild.setChildText(getString(R.string.child_text, i));
            childItemList.add(horizontalChild);

            // Evens get 2 children, odds get 1
            if (i % 2 == 0) {
                HorizontalChild horizontalChild2 = new HorizontalChild();
                horizontalChild2.setChildText(getString(R.string.second_child_text, i));
                childItemList.add(horizontalChild2);
            }

            HorizontalParent horizontalParent = new HorizontalParent();
            horizontalParent.setChildItemList(childItemList);
            horizontalParent.setParentNumber(i);
            horizontalParent.setParentText(getString(R.string.parent_text, i));
            if (i == 0) {
                horizontalParent.setInitiallyExpanded(true);
            }
            horizontalParentList.add(horizontalParent);
        }

        return horizontalParentList;
    }
}
