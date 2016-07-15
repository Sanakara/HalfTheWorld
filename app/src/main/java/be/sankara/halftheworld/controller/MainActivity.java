package be.sankara.halftheworld.controller;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.sankara.halftheworld.database.DbHelper2;
import be.sankara.halftheworld.database.UserDAO;
import be.sankara.halftheworld.model.User;
import database.thing.R;

public class MainActivity extends AppCompatActivity {

    EditText userName, passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText)findViewById(R.id.userName);
        passWord = (EditText)findViewById(R.id.passW);

        DbHelper2 dbHelper2 = new DbHelper2(this);
    }

    public void onLogMeIn (View v){

        UserDAO userDAO = new UserDAO(this);
        SQLiteDatabase db = userDAO.getDatabase();
        userDAO.openReadable();
        User lambda = userDAO.getUserByUsername(userName.getText().toString());
        if(lambda == null){
            userName.setError(getString(R.string.errorUsername));
            userName.setText("");
        }else{
            if(passWord.getText().toString().equals(lambda.getPassword())){
                Intent alice = new Intent(this, GooglePlacesActivity.class);
                startActivity(alice);
                passWord.setText("");

            }else {
                passWord.setError(getString(R.string.errorPassword));
                passWord.setText("");
            }

        }
        userDAO.close();
    }

    public void onStartRegistration (View vius){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
