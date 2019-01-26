package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private final String BASE_URL = "somesite.index.php?"; // put your url here
    private EditText passwordTXT, usernameTXT;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameTXT = findViewById(R.id.email);
        textView = findViewById(R.id.txtID);
        passwordTXT = findViewById(R.id.password);

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithVolley(usernameTXT.getText().toString(), passwordTXT.getText().toString());
            }
        });
    }

    private void loginWithVolley(final String username, final String password) {

        RequestQueue mRequestQueue = Volley.newRequestQueue(LoginActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        }
        ) {
            @Override // to add params use this
            protected Map<String, String> getParams() {
                // this work like this:
                //  ...url...?username=admin&password=123
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                return map;
            }
        };
        mRequestQueue.add(stringRequest);
    }
}

