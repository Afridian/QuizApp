package starters.quizthroughxml;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class SignUp extends AppCompatActivity {
    EditText etSUID, etSPass, etSCPass;
    Button btnSignUp;
    TextView tvSignIn;
    quizDatabase mydb;
    User user;
    String UName;

    /*static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users "
                    + " ADD COLUMN last_update INTEGER");
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSUID = (EditText)findViewById(R.id.etSUID);
        etSCPass = (EditText)findViewById(R.id.etSCPass);
        etSPass = ( EditText)findViewById(R.id.etSPass);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        tvSignIn = (TextView)findViewById(R.id.tvSignIn);



        mydb = Room.databaseBuilder(this.getApplicationContext(), quizDatabase.class, "user-database")
                .allowMainThreadQueries().build();

        user = new User();

        List<User> CheckUser = mydb.userDao().getAll();
        for (User user : CheckUser){

            UName = user.getName();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etSUID.getText().toString()) || TextUtils.isEmpty(etSPass.getText().toString()) || TextUtils.isEmpty(etSCPass.getText().toString())) {

                    etSUID.setError("Enter Username");
                    etSUID.requestFocus();
                    etSPass.setError("Enter Password");
                    etSPass.requestFocus();
                    etSCPass.setError("Repeat Password");
                    etSCPass.requestFocus();

                    return;
                }if (etSPass.getText().toString().equals(etSCPass.getText().toString())) {

                    user.setName(etSUID.getText().toString());
                    user.setPassword(etSPass.getText().toString());


                        if (etSUID.getText().toString().equalsIgnoreCase(UName)){

                            Toast.makeText(SignUp.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                        }else {

                            mydb.userDao().insertUser(user);
                            Toast.makeText(SignUp.this, "User Registered Successfully", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(SignUp.this,Login.class);
                            startActivity(intent);
                        }

                } else {

                    Toast.makeText(SignUp.this, "Passwords Do not match! Please try Again ", Toast.LENGTH_LONG).show();
                }

            }

        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
