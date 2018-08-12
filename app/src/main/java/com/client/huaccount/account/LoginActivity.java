package com.client.huaccount.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.client.huaccount.MainActivity;
import com.client.huaccount.R;
import com.client.huaccount.base.BaseActivity;
import com.client.huaccount.bean.LoginInfo;
import com.client.huaccount.bean.UserInfo;
import com.client.huaccount.http.MyCallBack;
import com.client.huaccount.http.UserRequest;
import com.client.huaccount.util.UserUtil;

import java.util.List;

/**
 * Created by l on 2018/8/1.
 */

public class LoginActivity extends BaseActivity {
   public static final String TAG = "LoginActivity";

   private EditText  mUsername_EditTxt = null;
   private EditText  mPassword_EditTxt = null;
   private TextView  mForget_psd_txt = null;
   private Button   mLogin_button = null;
   private Button   mRegister_button = null;
   private ProgressDialog mProgress;
   private String  username;
   private String  password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        setupBackAsUp(R.string.user_login);
        mUsername_EditTxt = (EditText)findViewById(R.id.et_user_name);
        mPassword_EditTxt = (EditText)findViewById(R.id.et_user_password);
        mForget_psd_txt = (TextView)findViewById(R.id.txt_forget);
        mLogin_button = (Button)findViewById(R.id.btn_login);
        mRegister_button = (Button)findViewById(R.id.btn_register);

        initListener();
    }

    private void initListener() {
        mForget_psd_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsername_EditTxt.getText().toString().trim();
                password = mPassword_EditTxt.getText().toString().trim();

                if (UserUtil.CheckLogin(LoginActivity.this, username, password)){
                    sendLoginRequest();
                }
            }
        });

        mRegister_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendLoginRequest() {
        mProgress = new ProgressDialog(LoginActivity.this);
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.setMessage(getResources().getString(R.string.txt_loading));
        mProgress.setCancelable(true);
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();

        UserRequest.userLogin(username, password, 0x001, callBack);
    }

    private MyCallBack callBack = new MyCallBack() {
        @Override
        public void onSuccess(int what, Object result) {
            Log.d(TAG, "onSuccess" + result.toString());
            Toast.makeText(LoginActivity.this, getText(R.string.user_login_success), Toast.LENGTH_SHORT).show();
            try {
                LoginInfo userInfo = (LoginInfo) result;
                userInfo.setUsername(username);
                userInfo.setPassword(password);
            }catch (Exception e){
                e.printStackTrace();
            }

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            mProgress.dismiss();
        }

        @Override
        public void onSuccessList(int what, List results) {
            Log.d(TAG, "onSuccessList" + results.toString());
        }

        @Override
        public void onFail(int what, String result) {
            Log.d(TAG, "onFail" + result.toString());
            switch (what){
                case 1:
                    Toast.makeText(LoginActivity.this, getText(R.string.account_password_error), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(LoginActivity.this, getText(R.string.account_no_exists), Toast.LENGTH_SHORT).show();
                    break;
            }
            mProgress.dismiss();
        }
    };

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }
}
