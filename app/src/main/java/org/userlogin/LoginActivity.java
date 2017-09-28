package org.userlogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.userlogin.controler.SQLiteHandler;
import org.userlogin.controler.SessionManager;

@SuppressWarnings("deprecation")
public class LoginActivity extends Activity {

    private static final String TAG = RegesterActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();

                //cek panjang string
                if(email.length() <= 0) {
                    showToast("Email masih kosong");
                    return;
                }

                // cek konstruksi email
                String[] s = email.split("@");
                if(s.length != 2){
                    showToast("Email salah");
                    return;
                }

                // cek array s ke 2 ada titik nya
                if(!s[1].contains(".")) {
                    showToast("Email salah");
                    return;
                }

                String password = inputPassword.getText().toString();

                //cek panjang string
                if(password.length() <= 0) {
                    showToast("Password masih kosong");
                    return;
                }

                login(email, password);
            }
        });

    }

    public void login(String email, String password) {
        String url = "http://www.eggwardslab.com/habib/login.php?email="+email+"&password="+password;
        final RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Json parser disni utk parsing respose
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jr = jsonObject.getJSONArray("user");

                            for (int i = 0; i <jr.length(); i++){

                                JSONObject jo = jr.getJSONObject(i);

                                String email = jo.getString("email");
                                String password = jo.getString("password");

                                jsonObject.put("email", email);
                                jsonObject.put("password", password);




                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        showToast("Login sukses");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast("Login gagal."+error.getMessage());
                    }
                }
        );
        queue.add(stringRequest);
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
