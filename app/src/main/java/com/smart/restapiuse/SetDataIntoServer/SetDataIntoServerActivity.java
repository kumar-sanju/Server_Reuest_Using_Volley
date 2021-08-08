package com.smart.restapiuse.SetDataIntoServer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smart.restapiuse.R;

import java.util.HashMap;
import java.util.Map;

public class SetDataIntoServerActivity extends AppCompatActivity {

    TextView tv1;
    Button b1;
    EditText t1, t2;
//    private static final String url = "http://localhost/api/setData.php"; // for
    private static final String url = "http://10.0.2.2/api/setData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_data_into_server);

        b1 = findViewById(R.id.b1);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        tv1 = findViewById(R.id.tv1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processData(t1.getText().toString(), t2.getText().toString());
            }
        });
    }

    private void processData(final String name, final String email) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                t1.setText("");
                t2.setText("");
                tv1.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t1.setText("");
                t2.setText("");
                tv1.setText(error.toString());
                tv1.setTextColor(Color.RED);
                tv1.setTextSize(14);
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("name",name);
                map.put("email",email);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }


}