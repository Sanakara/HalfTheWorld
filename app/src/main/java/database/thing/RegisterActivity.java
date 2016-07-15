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

public class RegisterActivity extends AppCompatActivity {

    EditText email, username, password, confirm, userIdTester;
    TextView userData;
    SQLiteDatabase db;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

    public void showMeUserData(View vi){
        UserDAO userDAO = new UserDAO(this);
        userDAO.openReadable();
        User lambda = userDAO.getUserById(Integer.parseInt(userIdTester.getText().toString()));
        if(lambda != null){
            userData.setText(lambda.getUserName() + " email = "+ lambda.getEmail() + "\n"
                    + lambda.getUserName() + " password = " + lambda.getPassword());
        }else {
            Cursor mCursor = userDAO.getDatabase().rawQuery("SELECT * FROM User", null);
            StringBuilder builder = new StringBuilder();
            mCursor.moveToFirst();
            do{
                User bi = userDAO.cursorToUser(mCursor);
                builder.append(bi.getUserName() +"\nuserId = "+ bi.getUserId() +"\nemail = "+ bi.getEmail() + "\npasssword = " + bi.getPassword()+"\n\n");
            }while(mCursor.moveToNext());

            userData.setText(builder);





        }

    }
}
