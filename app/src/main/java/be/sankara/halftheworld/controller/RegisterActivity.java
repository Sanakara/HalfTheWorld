package be.sankara.halftheworld.controller;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import be.sankara.halftheworld.database.DbHelper2;
import be.sankara.halftheworld.database.UserDAO;
import be.sankara.halftheworld.model.User;
import database.thing.R;

public class RegisterActivity extends AppCompatActivity {

    EditText email, username, password, confirm, userIdTester;
    TextView userData;
    SQLiteDatabase db;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText)findViewById(R.id.email);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.passW);
        userData = (TextView)findViewById(R.id.userData);
        userIdTester = (EditText)findViewById(R.id.userIdTest);
        confirm = (EditText)findViewById(R.id.confirm);

        DbHelper2 dbHelper2 = new DbHelper2(this);
    }

    public void onRegister (View v){

        if((password.getText()).toString().equals(confirm.getText().toString())){

            u = new User();
            u.setEmail(email.getText().toString());
            u.setUserName(username.getText().toString());
            u.setPassword(password.getText().toString());

            UserDAO userDAO = new UserDAO(this);
            userDAO.openWritable();
            try{
                userDAO.insert(u);
            }catch(SQLiteConstraintException e){
                Toast.makeText(getApplicationContext(), "Cet username ou cet email est déjà usité", Toast.LENGTH_SHORT).show();
            }

            email.setText("");
            username.setText("");
            password.setText("");
            confirm.setText("");
            userDAO.close();

            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);

        }else{
            confirm.setError(getString(R.string.errorPassword));
        }

    }

}
