package starters.quizthroughxml;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murad on 11/20/2017.
 */
public class Defficulty_FragmentDialog extends DialogFragment {

    List<String> Q_Difficulty;
    RecyclerView rv;
    MyAdapter adapter;
    MainActivity obj;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        obj = (MainActivity)context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_layout,container,false);
             return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);

        getDialog().setTitle("Select Quiz Category");
        Q_Difficulty = new ArrayList<String>();

        Q_Difficulty.add(Utils.DIFFICULTY_EASY);
        Q_Difficulty.add(Utils.DIFFICULTY_MEDIUM);
        Q_Difficulty.add(Utils.DIFFICULTY_HARD);

        rv = (RecyclerView)view.findViewById(R.id.CategoryList);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new MyAdapter(this.getActivity(), Q_Difficulty, new ItemClickListener() {
            @Override
            public void onClick(String Category) {

                obj.selection(Category);
                getDialog().dismiss();
            }
        });
        rv.setAdapter(adapter);







    }
}
