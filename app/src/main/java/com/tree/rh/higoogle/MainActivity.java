package com.tree.rh.higoogle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.tree.rh.googlelib.HiGoogle;
import com.tree.rh.googlelib.OnLoginListener;

public class MainActivity extends AppCompatActivity {

    SignInButton signInButton;

    HiGoogle hiGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.googleSignInB);

        hiGoogle = new HiGoogle(this, this);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiGoogle.signIn(new OnLoginListener() {
                    @Override
                    public void onSuccess(GoogleSignInAccount account) {
                        display("Sign in Successful");
                    }
                    @Override
                    public void onFailed(String why) {
                        display("Sign in failed");
                    }
                });
            }
        });

    }//onCreateEND

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hiGoogle.fromActivityResult(requestCode, data);
    }

    public void display(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}//classEND
