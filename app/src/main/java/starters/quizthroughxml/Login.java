package starters.quizthroughxml;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity {

    EditText etUid, etPass;
    Button btnSignIn;
    TextView tvSignUp;

    quizDatabase mydb;
    User user;
    String UName, UPass;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUid = (EditText)findViewById(R.id.etUid);
        etPass = (EditText)findViewById(R.id.etPass);
        tvSignUp = (TextView)findViewById(R.id.tvSignUp);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);



        mydb = Room.databaseBuilder(this.getApplicationContext(), quizDatabase.class, "user-database")
                .allowMainThreadQueries().build();

        user = new User();

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });


        List<User> CheckUser = mydb.userDao().getAll();
        for (User user : CheckUser){

            UName = user.getName();
            UPass = user.getPassword();

            }

            sp = getSharedPreferences("LoginDetails", 0);
        if (!(sp.getString("username","").isEmpty())){
            Intent intent = new Intent(Login.this,DashBoard.class);
            startActivity(intent);
        }



        btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {

                    if (etUid.getText().toString().equalsIgnoreCase(UName) && etPass.getText().toString().equalsIgnoreCase(UPass)){

                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,DashBoard.class);
                        startActivity(intent);

                         sp = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
                         editor = sp.edit();
                         editor.putString("username", etUid.getText().toString());
                         editor.commit();




                    }
                    else {

                        Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }

                }

        });





    }
}
