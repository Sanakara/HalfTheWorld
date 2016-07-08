package database.thing;

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

    EditText userName, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.passW);

        DbHelper2 dbHelper2 = new DbHelper2(this);
    }

    public void onLogMeIn (View v){

        UserDAO userDAO = new UserDAO(this);
        SQLiteDatabase db = userDAO.getDatabase();
        userDAO.openReadable();
        User lambda = userDAO.getUserByUsername(userName.getText().toString());

        if(lambda == null){
            userName.setError(getString(R.string.errorUsername));
        }else{
            if((password.getText().toString()).equals(lambda.getPassword())){
                //TODO API GOOGLE MAPS
            } else{
                password.setError(getString(R.string.errorPassword));
            }
        }


    }


}
