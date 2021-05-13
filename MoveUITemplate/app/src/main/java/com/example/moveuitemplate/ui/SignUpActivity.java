package com.example.moveuitemplate.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moveuitemplate.R;
import com.example.moveuitemplate.utils.CheckConnection;
import com.example.moveuitemplate.utils.Server;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    //Tao tai khoan
    EditText edtTaiKhoan, edtMatKhau;
    Button btnTaoTaiKhoan;

    //Dang nhap
    Button btnLoginHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anhxa();

        //Kiểm tra kết nối đến PHP Server, nếu có kết nối thì cho bắt sự kiện đăng kí
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            EventButton();
        } else {
            CheckConnection.ShowToast(getApplicationContext(), "Kiểm tra lại kết nối!");

        }

    }

    private void EventButton() {

        btnTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tdn = edtTaiKhoan.getText().toString().replaceAll("\\s+","");
                final String mk = edtMatKhau.getText().toString().replaceAll("\\s+","");

                if (tdn.length() > 0 && tdn.length() <= 15 && mk.length() >0 && mk.length() <= 15) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlSignUp, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("*** Trang thai: ", response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("tendangnhap", tdn);
                            hashMap.put("matkhau", mk);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                }
                else {
                    CheckConnection.ShowToast(getApplicationContext(), "Kiểm tra lại dữ liệu nhập vào!");
                }


            }
        });

        btnLoginHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //super.onCreate(savedInstanceState);
                //setContentView(R.layout.activity_login);
                finish();
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa () {
        edtTaiKhoan = findViewById(R.id.edt_userSignUp);
        edtMatKhau = findViewById(R.id.edt_passwordSignUp);
        btnTaoTaiKhoan = findViewById(R.id.btn_taoTaiKhoan);

        btnLoginHome = findViewById(R.id.btn_loginHome);

    }
}