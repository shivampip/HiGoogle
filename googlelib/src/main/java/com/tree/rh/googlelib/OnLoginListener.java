package com.tree.rh.googlelib;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by Sittu Agrawal on 05-12-2017.
 */

public interface OnLoginListener {

    public void onSuccess(GoogleSignInAccount account);
    public void onFailed(String why);

}
