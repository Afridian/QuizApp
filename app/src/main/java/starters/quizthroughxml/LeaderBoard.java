package starters.quizthroughxml;

import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LeaderBoard extends AppCompatActivity {

    RecyclerView RvScoreLead;
    quizDatabase mydb;
    User user;
    List<User> userList;
    ScoreLead_Adapter leadAdapter;
    String UName, UScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        mydb = Room.databaseBuilder(this.getApplicationContext(), quizDatabase.class, "user-database")
                .allowMainThreadQueries().build();

        RvScoreLead = (RecyclerView)findViewById(R.id.RvScoreLead);
        RvScoreLead.setLayoutManager(new LinearLayoutManager(this));
        RvScoreLead.setHasFixedSize(true);
        RvScoreLead.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        user = new User();


        userList = mydb.userDao().getAll();

        leadAdapter = new ScoreLead_Adapter(this, userList);
        RvScoreLead.setAdapter(leadAdapter);

        }

    }
