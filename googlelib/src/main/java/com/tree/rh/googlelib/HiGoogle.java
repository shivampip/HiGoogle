package com.tree.rh.googlelib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class HiGoogle {

    private Context context;
    private Activity activity;

    private static final String TAG= "HiGoogleLog";
    private static final int RC_SIGN_IN = 3012;//My Birthday 30 Dec
    private GoogleSignInClient mGoogleSignInClient;


    public HiGoogle(Context context, Activity activity){
        this.context= context;
        this.activity= activity;
        initGoogle();
    }

    public HiGoogle(Context context, Activity activity, String accessToken){
        this.context= context;
        this.activity= activity;
        initGoogle(accessToken);
    }

    private void initGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    private void initGoogle(String accessToken) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(accessToken)
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    private OnLoginListener onLoginListener;
    public void signIn(OnLoginListener onLoginListener){
        this.onLoginListener= onLoginListener;
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void fromActivityResult(int requestCode, Intent data){
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                onLoginListener.onSuccess(account);
            } catch (ApiException e) {
                onLoginListener.onFailed(e+"");
            }
        }
    }




}//classEND
