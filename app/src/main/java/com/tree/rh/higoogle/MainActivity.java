package com.tree.rh.higoogle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.tree.rh.googlelib.HiGoogle;
import com.tree.rh.googlelib.OnLoginListener;

public class MainActivity extends AppCompatActivity {

    SignInButton signInButton;
    TextView tv;
    ImageView profileIv;

    HiGoogle hiGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.googleSignInB);
        tv = findViewById(R.id.tv);
        profileIv = findViewById(R.id.profilePicIv);

        hiGoogle = new HiGoogle(this, this);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiGoogle.signIn(new OnLoginListener() {
                    @Override
                    public void onSuccess(GoogleSignInAccount account) {
                        display("Sign in Successful");
                        tv.setText("SignIn Successful");
                        tv.append("\nEmail:- " + account.getEmail());
                        tv.append("\nName:- " + account.getDisplayName());
                        Glide.with(MainActivity.this).load(account.getPhotoUrl()).into(profileIv);
                        tv.append("\nID:- "+account.getId());
                    }

                    @Override
                    public void onFailed(String why) {
                        display("Sign in failed");
                        tv.setText("Sign in failed due to:- \n"+why);
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
