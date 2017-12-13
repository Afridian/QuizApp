package starters.quizthroughxml;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvQ, tvTrue, tvFalse, tvCorect, tvInCorrect, tvScore, tvDeff, tvCateg;
    Button btnPrev, btnNext, btnTrue, btnFalse, btnFinish;

    int counter = 0;
    int score = 0;
    ProgressBar pbar;
    String Q, A, Q_URL;

    int SELECTED_CATEGORY = Utils.CATEGORY_COMPUTER;
    String SELECTED_DIFFICULTY = Utils.DIFFICULTY_EASY;


    List<ListClass> Alist;

    ListClass QList;
    RequestQueue requestQueue;
    FragmentManager fm;
    FragmentManager ft;
    Category_FragmentDialog CFD;
    Defficulty_FragmentDialog DFD;


    public String setUrl(int category, String difficulty) {

        Q_URL = "https://opentdb.com/api.php?amount=10&category=" + category + "&difficulty=" + difficulty + "&type=boolean";

        return Q_URL;
    }

    public String setDifficulty(int category){
        String CATEGORY = "";
        switch (category){
            case Utils.CATEGORY_COMPUTER:
                CATEGORY = "Computer";
                break;
            case Utils.CATEGORY_GENERALKNOWLEDGE:
                CATEGORY = "General Knowledge";
                break;
            case Utils.CATEGORY_HISTORY:
                CATEGORY = "History";
                break;
            case Utils.CATEGORY_GEOGRAPHY:
                CATEGORY = "Geography";
                break;
        }

        return CATEGORY;
    }

    public void callServices(int category, String difficulty) {


        tvQ.setVisibility(View.GONE);
        pbar.setVisibility(View.VISIBLE);

        tvCateg.setText("Category: Computer");
        tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);

        final String url = setUrl(category, difficulty);

        JsonObjectRequest Jrequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.d("URL", url);
                    Log.d("JSON_RESPONSE", response.toString());
                    JSONArray jsonArray = response.getJSONArray("results");

                    if (jsonArray!=null && jsonArray.length()>0) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject QUESTIONS = jsonArray.getJSONObject(i);

                            if (Build.VERSION.SDK_INT >= 24) {

                                Q = Html.fromHtml(QUESTIONS.getString("question"), Html.FROM_HTML_MODE_LEGACY).toString();
                            } else {
                                Q = Html.fromHtml(QUESTIONS.getString("question")).toString();
                            }

                                A = QUESTIONS.getString("correct_answer");
                            QList = new ListClass(Q, A);
                            Alist.add(QList);

                            tvQ.setTextColor(Color.rgb(241,247,237));
                            tvQ.setText(Alist.get(counter).getQuestions());

                        }

                    }
                     else {

                               tvQ.setTextColor(Color.RED);
                               tvQ.setText("Data is not available");
                           }


                    pbar.setVisibility(View.GONE);
                    tvQ.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    tvQ.setTextColor(Color.RED);
                    tvQ.setText("Data is not available");
                    //e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pbar.setVisibility(View.GONE);
                tvQ.setTextColor(Color.RED);
                tvQ.setText("Data is not available");

            }
        });

        requestQueue.add(Jrequest);


    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu categoryMenu = menu.addSubMenu("Category");
        SubMenu difficultyMenu = menu.addSubMenu("Difficulty");
        categoryMenu.add(Utils.CATEGORY, Utils.COMPUTER_MENU_ITEM, 0, "Computer");
        categoryMenu.add(Utils.CATEGORY, Utils.GENERAL_KNOWLEDGE_MENU_ITEM, 1, "General Knowledge");
        categoryMenu.add(Utils.CATEGORY, Utils.HISTORY_MENU_ITEM, 2, "History");
        categoryMenu.add(Utils.CATEGORY, Utils.GEOGRAPHY_MENU_ITEM, 3, "Geography");

        difficultyMenu.add(Utils.DIFFICULTY, Utils.EASY_MENU_ITEM, 0, "Easy");
        difficultyMenu.add(Utils.DIFFICULTY, Utils.MEDIUM_MENU_ITEM, 1, "Medium");
        difficultyMenu.add(Utils.DIFFICULTY, Utils.HARD_MENU_ITEM, 2, "Hard");


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case Utils.COMPUTER_MENU_ITEM:

                Alist.clear();
                tvQ.setVisibility(View.GONE);
                callServices(Utils.CATEGORY_COMPUTER, Utils.DIFFICULTY_EASY);
                tvQ.setVisibility(View.VISIBLE);
                tvCateg.setText("Category: Computer");
                tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                break;

            case Utils.GENERAL_KNOWLEDGE_MENU_ITEM:

                Alist.clear();
                callServices(Utils.CATEGORY_GENERALKNOWLEDGE, Utils.DIFFICULTY_EASY);
                tvCateg.setText("Category: General Knowledge");
                tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                break;

            case Utils.HISTORY_MENU_ITEM:

                Alist.clear();
                callServices(Utils.CATEGORY_HISTORY, Utils.DIFFICULTY_EASY);
                tvCateg.setText("Category: History");
                tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                break;

            case Utils.GEOGRAPHY_MENU_ITEM:

                Alist.clear();
                callServices(Utils.CATEGORY_GEOGRAPHY, Utils.DIFFICULTY_EASY);
                tvCateg.setText("Category: Geography");
                tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                break;

            case Utils.EASY_MENU_ITEM:

                Alist.clear();
                callServices(SELECTED_CATEGORY, Utils.DIFFICULTY_EASY);
                tvCateg.setText("Category: Computer");
                tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_EASY);
                break;

            case Utils.MEDIUM_MENU_ITEM:

                Alist.clear();
                callServices(SELECTED_CATEGORY, Utils.DIFFICULTY_MEDIUM);
                tvCateg.setText("Category: Computer");
                tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_MEDIUM);
                break;


            case Utils.HARD_MENU_ITEM:

                Alist.clear();
                callServices(SELECTED_CATEGORY, Utils.DIFFICULTY_HARD);
                tvCateg.setText("Category: Computer");
                tvDeff.setText("Difficulty: "+Utils.DIFFICULTY_HARD);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
*/

public void selection(String selection){

    switch (selection) {

        case "Computer":
            Alist.clear();
            SELECTED_CATEGORY = Utils.CATEGORY_COMPUTER;
            SELECTED_DIFFICULTY = Utils.DIFFICULTY_EASY;
            tvQ.setVisibility(View.GONE);
            callServices(SELECTED_CATEGORY, SELECTED_DIFFICULTY);
            tvQ.setVisibility(View.VISIBLE);
            tvCateg.setText("Category: Computer");
            tvDeff.setText("Difficulty: "+ SELECTED_DIFFICULTY);
            break;

        case "General Knowledge":
            Alist.clear();
            SELECTED_CATEGORY = Utils.CATEGORY_GENERALKNOWLEDGE;
            SELECTED_DIFFICULTY = Utils.DIFFICULTY_EASY;
            callServices(SELECTED_CATEGORY, SELECTED_DIFFICULTY);
            tvCateg.setText("Category: General Knowledge");
            tvDeff.setText("Difficulty: "+SELECTED_DIFFICULTY);
            break;

        case "History":
            Alist.clear();
            SELECTED_CATEGORY = Utils.CATEGORY_HISTORY;
            SELECTED_DIFFICULTY = Utils.DIFFICULTY_EASY;
            callServices(SELECTED_CATEGORY, SELECTED_DIFFICULTY);
            tvCateg.setText("Category: History");
            tvDeff.setText("Difficulty: "+SELECTED_DIFFICULTY);
            break;

        case "Geography":
            Alist.clear();
            SELECTED_CATEGORY = Utils.CATEGORY_GEOGRAPHY;
            SELECTED_DIFFICULTY = Utils.DIFFICULTY_EASY;
            callServices(SELECTED_CATEGORY, SELECTED_DIFFICULTY);
            tvCateg.setText("Category: Geography");
            tvDeff.setText("Difficulty: "+SELECTED_DIFFICULTY);
            break;

        case Utils.DIFFICULTY_EASY:
            Alist.clear();
            SELECTED_DIFFICULTY = Utils.DIFFICULTY_EASY;
                callServices(SELECTED_CATEGORY, SELECTED_DIFFICULTY);
                tvCateg.setText("Category: "+setDifficulty(SELECTED_CATEGORY));
                tvDeff.setText("Difficulty: "+SELECTED_DIFFICULTY);
            break;

        case Utils.DIFFICULTY_MEDIUM:
            Alist.clear();
            SELECTED_DIFFICULTY = Utils.DIFFICULTY_MEDIUM;
                callServices(SELECTED_CATEGORY, SELECTED_DIFFICULTY);
                tvCateg.setText("Category: "+setDifficulty(SELECTED_CATEGORY));
                tvDeff.setText("Difficulty: "+SELECTED_DIFFICULTY);
            break;


        case Utils.DIFFICULTY_HARD:
            Alist.clear();
            SELECTED_DIFFICULTY = Utils.DIFFICULTY_HARD;
                callServices(SELECTED_CATEGORY, SELECTED_DIFFICULTY);
                tvCateg.setText("Category: "+setDifficulty(SELECTED_CATEGORY));
                tvDeff.setText("Difficulty: "+SELECTED_DIFFICULTY);
            break;
    }


}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        tvQ = (TextView) findViewById(R.id.tvQ);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        tvCorect = (TextView) findViewById(R.id.tvCorrect);
        tvInCorrect = (TextView) findViewById(R.id.tvInCorrect);
        tvScore = (TextView) findViewById(R.id.tvScore);
        btnNext = (Button) findViewById(R.id.btnNext);
       // btnPrev = (Button) findViewById(R.id.btnPrev);
        btnFinish = (Button)findViewById(R.id.btnFinish);
        requestQueue = Volley.newRequestQueue(this);
        pbar = (ProgressBar) findViewById(R.id.pbar);
        tvDeff = (TextView)findViewById(R.id.tvDeff);
        tvCateg = (TextView)findViewById(R.id.tvCateg);
        Alist = new ArrayList<ListClass>();

        fm = getSupportFragmentManager();
        CFD = new Category_FragmentDialog();
        DFD = new Defficulty_FragmentDialog();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        callServices(Utils.CATEGORY_COMPUTER, Utils.DIFFICULTY_EASY);

        btnNext.setEnabled(false);
       // btnPrev.setEnabled(false);

        tvScore.setText("Score: " + counter);

        tvCateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CFD.show(fm, "CATEGORY_TAG");


            }
        });

        tvDeff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DFD.show(fm, "DEFFICULTY_TAG");
            }
        });


       /* btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnNext.setEnabled(false);
                btnPrev.setEnabled(false);
                btnTrue.setEnabled(true);
                btnFalse.setEnabled(true);

                tvCorect.setText("");
                tvInCorrect.setText("");
                counter--;
                if (counter < 0) {

                    counter = Alist.size() - 1;
                }
                tvQ.setText(Alist.get(counter).getQuestions());

            }
        });*/

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnNext.setEnabled(true);
               // btnPrev.setEnabled(true);

                String rAnswer = btnTrue.getText().toString();
                checkAnswer(rAnswer);

                if (isCorrect(rAnswer)) {
                    score = score + 5;
                    tvScore.setText("Score: " + score);

                } else {
                    score = score - 5;
                    if (score < 0) {

                        score = 0;
                    }
                    tvScore.setText("Score: " + score);
                }


                btnTrue.setEnabled(false);
                btnFalse.setEnabled(false);


            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnNext.setEnabled(true);
                //btnPrev.setEnabled(true);

                String wAnswer = btnFalse.getText().toString();
                checkAnswer(wAnswer);

                if (isCorrect(wAnswer)) {
                    score = score + 5;
                    tvScore.setText("Score: " + score);

                } else {
                    score = score - 5;
                    if (score < 0) {

                        score = 0;
                    }
                    tvScore.setText("Score: " + score);
                }

                btnTrue.setEnabled(false);
                btnFalse.setEnabled(false);

            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnNext.setEnabled(false);
                //btnPrev.setEnabled(false);
                btnTrue.setEnabled(true);
                btnFalse.setEnabled(true);

                tvCorect.setText("");
                tvInCorrect.setText("");
                counter++;
                if (counter >= Alist.size()) {

                   if (counter>=9){
                        btnNext.setVisibility(View.GONE);
                        btnFinish.setVisibility(View.VISIBLE);
                        btnTrue.setEnabled(false);
                        btnFalse.setEnabled(false);

                    }

                }
                else {
                    tvQ.setText(Alist.get(counter).getQuestions());
                }

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                intent.putExtra("EarnedScore",score);
                startActivity(intent);


            }
        });



    }

    public boolean isCorrect(String ans) {

        return (ans.equalsIgnoreCase(Alist.get(counter).getAnswers()));
    }

    public void checkAnswer(String answer) {

        if (isCorrect(answer)) {

            tvCorect.setVisibility(View.VISIBLE);
            tvInCorrect.setVisibility(View.GONE);

            tvCorect.setText("Correct");
        } else {

            tvCorect.setVisibility(View.GONE);
            tvInCorrect.setVisibility(View.VISIBLE);

            tvInCorrect.setText("InCorrect");
        }
    }


}
