package starters.quizthroughxml;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Murad on 11/23/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public List<String> Q_Category;

    Context context;
    ItemClickListener listener;

    public MyAdapter(Context context, List<String> Q_Category, ItemClickListener listener){

        this.Q_Category = Q_Category;
        this.context = context;
        this.listener = listener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvViewHolder.setText(Q_Category.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(Q_Category.get(holder.getLayoutPosition()));
            }
        });

    }


    @Override
    public int getItemCount() {
        return Q_Category.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvViewHolder;
        View view;


        public MyViewHolder(View itemView) {
            super(itemView);

            tvViewHolder = (TextView) itemView.findViewById(R.id.tvViewHolder);
            view = itemView;

        }

    }

}
