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
    MainActivity obj;

    public MyAdapter(Context context, List<String> Q_Category){

        this.Q_Category = Q_Category;
        this.context = context;
        this.obj = new MainActivity();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvViewHolder.setText(Q_Category.get(position));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (Q_Category.get(position).equalsIgnoreCase("Computer")){
                   obj.Alist.clear();
                   obj.tvQ.setVisibility(View.GONE);
                   obj.callServices(Utils.CATEGORY_COMPUTER, Utils.DIFFICULTY_EASY);
                   obj.tvQ.setVisibility(View.VISIBLE);
                   obj.tvCateg.setText("Category: Computer");
                   obj.tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                }
                else if (Q_Category.get(position).equalsIgnoreCase("General Knowledge")){
                    obj.Alist.clear();
                    obj.tvQ.setVisibility(View.GONE);
                    obj.callServices(Utils.CATEGORY_GENERALKNOWLEDGE, Utils.DIFFICULTY_EASY);
                    obj.tvQ.setVisibility(View.VISIBLE);
                    obj.tvCateg.setText("Category: General Knowledge");
                    obj.tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                }
                else if (Q_Category.get(position).equalsIgnoreCase("History")){
                    obj.Alist.clear();
                   obj.tvQ.setVisibility(View.GONE);
                    obj.callServices(Utils.CATEGORY_HISTORY, Utils.DIFFICULTY_EASY);
                    obj.tvQ.setVisibility(View.VISIBLE);
                    obj.tvCateg.setText("Category: History");
                    obj.tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                }
                else if (Q_Category.get(position).equalsIgnoreCase("Geography")){
                    obj.Alist.clear();
                    obj.tvQ.setVisibility(View.GONE);
                    obj.callServices(Utils.CATEGORY_GEOGRAPHY, Utils.DIFFICULTY_EASY);
                    obj.tvQ.setVisibility(View.VISIBLE);
                    obj.tvCateg.setText("Category: Geography");
                    obj.tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                }



            }
        });

    }


    @Override
    public int getItemCount() {
        return Q_Category.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvViewHolder;
        ItemClickListener itemClickListener;


        public MyViewHolder(View itemView) {
            super(itemView);

            tvViewHolder = (TextView) itemView.findViewById(R.id.tvViewHolder);
            itemView.setOnClickListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener ){

            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {

            itemClickListener.onClick(view, getAdapterPosition());

        }
    }

}
