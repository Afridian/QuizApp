package starters.quizthroughxml;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Murad on 12/5/2017.
 */

public class ScoreLead_Adapter extends RecyclerView.Adapter<ScoreLead_Adapter.ScoreViewHolder> {

    public List<User> userList;
    Context context;

    public ScoreLead_Adapter(Context context, List<User> userList){
        this.userList = userList;
        this.context = context;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.scorelead_viewholder,parent,false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScoreViewHolder holder, final int position) {

        holder.tvName.setText(userList.get(position).getName());
        holder.tvScore.setText(userList.get(position).getScore()+"");

        //Log.i("USER", userList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvScore;
        public ScoreViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvUName);
            tvScore = (TextView) itemView.findViewById(R.id.tvUScore);
        }

    }

}

