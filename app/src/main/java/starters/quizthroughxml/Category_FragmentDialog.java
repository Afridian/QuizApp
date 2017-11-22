package starters.quizthroughxml;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murad on 11/20/2017.
 */
public class Category_FragmentDialog extends DialogFragment{
    List<String> Q_Category;
    List<String> Q_Difficulty;
    RecyclerView rv;
    MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Q_Category = new ArrayList<String>();
        Q_Difficulty = new ArrayList<String>();

        Q_Category.add("Computer");
        Q_Category.add("General Knowledge");
        Q_Category.add("History");
        Q_Category.add("Geography");

        Q_Difficulty.add(Utils.DIFFICULTY_EASY);
        Q_Difficulty.add(Utils.DIFFICULTY_MEDIUM);
        Q_Difficulty.add(Utils.DIFFICULTY_HARD);



        View rootView = inflater.inflate(R.layout.category_layout,container,false);

        rv = (RecyclerView)rootView.findViewById(R.id.CategoryList);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        adapter = new MyAdapter(this.getActivity(), Q_Category);
        rv.setAdapter(adapter);



        this.getDialog().setTitle("Select Quiz Category");



        return rootView;
    }


}
