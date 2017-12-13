package starters.quizthroughxml;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murad on 11/20/2017.
 */
public class Category_FragmentDialog extends DialogFragment{
    List Q_Category;
    List<String> Q_Difficulty;
    RecyclerView rv;
    MyAdapter adapter;

    MainActivity obj;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        obj = (MainActivity) context;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        getDialog().setTitle("Select Quiz Defficulty");
        Q_Category = new ArrayList<>();


        Q_Category.add("Computer");
        Q_Category.add("General Knowledge");
        Q_Category.add("History");
        Q_Category.add("Geography");



        View rootView = inflater.inflate(R.layout.category_layout,container,false);

        rv = (RecyclerView)rootView.findViewById(R.id.CategoryList);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new MyAdapter(this.getActivity(), Q_Category, new ItemClickListener() {
            @Override
            public void onClick(String Category) {
               obj.selection(Category);
                getDialog().dismiss();

            }
        });
        rv.setAdapter(adapter);





        return rootView;
    }


}
