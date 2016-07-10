package database.thing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                Intent alice = new Intent(this, Main3Activity.class);
                startActivity(alice);

            }else {
                passWord.setError(getString(R.string.errorPassword));
                passWord.setText("");
            }
        }


    }

    public void onStartRegistration (View vius){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }


}
