package starters.quizthroughxml;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    TextView tvPName, tvPScore, tvAgain;
    SharedPreferences sp;
    quizDatabase mydb;
    User user;
    String USERNAME, Uname;
    int UserScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvPName = (TextView)findViewById(R.id.tvPName);
        tvPScore = (TextView)findViewById(R.id.tvPScore);
        tvAgain = (TextView)findViewById(R.id.tvAgain);
        mydb = Room.databaseBuilder(this.getApplicationContext(), quizDatabase.class, "user-database")
                .allowMainThreadQueries().build();

        user = new User();

        Intent RIntent = getIntent();
        UserScore = RIntent.getIntExtra("EarnedScore", 0);

        sp = getSharedPreferences("LoginDetails", 0);
        USERNAME = sp.getString("username","");

        List<User> CheckUser = mydb.userDao().getAll();
        for (User user : CheckUser){

            Uname = user.getName();
        }

        mydb.userDao().updateData(UserScore,Uname);

        if (UserScore>=20){
            tvPName.setTextColor(Color.rgb(241,247,237));
            tvPScore.setTextColor(Color.rgb(241,247,237));
            tvPName.setText("Congratulations "+USERNAME + " You Passed the Quiz ");
            tvPScore.setText("You Scored "+UserScore);
        }
        else {

            tvPName.setTextColor(Color.RED);
            tvPScore.setTextColor(Color.RED);
            tvPName.setText(USERNAME + " You Failed the Quiz ");
            tvPScore.setText("You Scored "+UserScore);



        }

        tvAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this,DashBoard.class);
                startActivity(intent);
            }
        });
    }
}
