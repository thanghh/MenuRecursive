package com.bignerdranch.expandablerecyclerviewsample.linear.vertical;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bignerdranch.expandablerecyclerviewsample.R;

import java.util.Arrays;
import java.util.List;

/**
 * Sample Activity for the vertical linear RecyclerView sample.
 * Uses ButterKnife to inject view resources.
 *
 * @author Ryan Brooks
 * @version 1.0
 * @since 5/27/2015
 */
public class VerticalLinearRecyclerViewSampleActivity extends AppCompatActivity {

    private MenuAdapter mAdapter;
    private ParentChildMenuAdapter mAdapterChildAdapter;
    static int count = 0;
    RecyclerView recyclerView;
    @NonNull
    public static Intent newIntent(Context context) {
        return new Intent(context, VerticalLinearRecyclerViewSampleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_sample);
         recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        createMenu();
        //        show(recipes);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void createMenu(){

        ParentMenuExpandnew tacoGroup = new ParentMenuExpandnew("tacoGroup" );
        ParentMenuExpandnew tacoGroup1 = new ParentMenuExpandnew("tacoGroup1");
        ParentMenuExpandnew tacoGroup2 = new ParentMenuExpandnew("tacoGroup2");
        ParentMenuExpandnew tacoGroup3 = new ParentMenuExpandnew("tacoGroup3");


        final List<ParentMenuExpandnew> recipes1 = Arrays.asList(tacoGroup1,tacoGroup3);
        final List<ParentMenuExpandnew> recipes2 = Arrays.asList(tacoGroup3,tacoGroup2);
        final List<ParentMenuExpandnew> recipes3 = Arrays.asList(tacoGroup2);
        tacoGroup.setmIngredients(recipes1);
        tacoGroup1.setmIngredients(recipes2);
        tacoGroup3.setmIngredients(recipes3);

        final List<ParentMenuExpandnew> recipes = Arrays.asList(tacoGroup,tacoGroup1);

        mAdapter = new MenuAdapter(this,0);
        mAdapter.addAll(recipes);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void show(List<ParentMenuExpand> list) {
//        for (int i = 0; i < list.size(); i++) {
//            Log.d("ItemMenuExpand", "show: " + list.get(i).getName());
//
//        }

        count++;
        if(count<=4){


            Log.d("ItemMenuExpand", "show: " + list.size() +" " + count);
            show(list);
            createMenu();
        }




    }
}
