package starters.quizthroughxml;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Murad on 11/23/2017.
 */

public class DefficultyAdapter extends RecyclerView.Adapter<DefficultyAdapter.MyViewHolder> {

    public List<String> Q_Defficulty;

    Context context;
    MainActivity obj;

    public DefficultyAdapter(Context context, List<String> Q_Defficulty){

        this.Q_Defficulty = Q_Defficulty;
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

        holder.tvViewHolder.setText(Q_Defficulty.get(position));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (Q_Defficulty.get(position).equalsIgnoreCase("Easy")){
                   obj.Alist.clear();
                   obj.tvQ.setVisibility(View.GONE);
                   obj.callServices(obj.SELECTED_CATEGORY, Utils.DIFFICULTY_EASY);
                   obj.tvQ.setVisibility(View.VISIBLE);
                   obj.tvCateg.setText("Category: Computer");
                   obj.tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                }
                else if (Q_Defficulty.get(position).equalsIgnoreCase("Medium")){
                    obj.Alist.clear();
                    obj.tvQ.setVisibility(View.GONE);
                    obj.callServices(obj.SELECTED_CATEGORY, Utils.DIFFICULTY_MEDIUM);
                    obj.tvQ.setVisibility(View.VISIBLE);
                    obj.tvCateg.setText("Category: Computer");
                    obj.tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_MEDIUM);
                }
                else if (Q_Defficulty.get(position).equalsIgnoreCase("Hard")){
                    obj.Alist.clear();
                   obj.tvQ.setVisibility(View.GONE);
                    obj.callServices(obj.SELECTED_CATEGORY, Utils.DIFFICULTY_HARD);
                    obj.tvQ.setVisibility(View.VISIBLE);
                    obj.tvCateg.setText("Category: Computer");
                    obj.tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_HARD);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return Q_Defficulty.size();
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
