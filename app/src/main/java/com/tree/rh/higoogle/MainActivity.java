package com.tree.rh.higoogle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.tree.rh.googlelib.HiGoogle;
import com.tree.rh.googlelib.OnLoginListener;

public class MainActivity extends AppCompatActivity {


    HiGoogle hiGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hiGoogle= new HiGoogle(this, this);
        hiGoogle.signIn(new OnLoginListener() {


            @Override
            public void onSuccess(GoogleSignInAccount account) {

            }

            @Override
            public void onFailed(String why) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hiGoogle.fromActivityResult(requestCode, data);
    }
}
