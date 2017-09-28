package org.userlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.userlogin.controler.SQLiteHandler;
import org.userlogin.controler.SessionManager;

import java.util.HashMap;

public class ActivityMain extends Activity {
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);


        db = new SQLiteHandler(getApplicationContext());


        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }


        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");


        txtName.setText(name);
        txtEmail.setText(email);


        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();


        Intent intent = new Intent(ActivityMain.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
