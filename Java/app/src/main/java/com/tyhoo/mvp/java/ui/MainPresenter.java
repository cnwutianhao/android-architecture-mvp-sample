package com.tyhoo.mvp.java.ui;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.tyhoo.mvp.java.data.User;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private final Context mContext;

    private final MainContract.View mMainView;

    private String jsonResponse;

    public MainPresenter(Context context, @NonNull MainContract.View mainView) {
        mContext = context;
        mMainView = mainView;
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        networkRequest();
    }

    private void networkRequest() {
        mMainView.setLoadingIndicator(true);

        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://api.github.com/users/cnwutianhao";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d(TAG, "networkRequest: success");
                    jsonResponse = response;
                    mMainView.setLoadingIndicator(false);
                    showUser(response);
                },
                error -> {
                    Log.d(TAG, "networkRequest: error");
                    mMainView.setLoadingIndicator(false);
                    showUser("");
                });

        queue.add(stringRequest);
    }

    private void showUser(String result) {
        if (Strings.isNullOrEmpty(result)) {
            mMainView.hideUser();
        } else {
            mMainView.showUser(result);
        }
    }

    @Override
    public void parseJson() {
        if (Strings.isNullOrEmpty(jsonResponse)) {
            return;
        }

        Gson gson = new Gson();
        User user = gson.fromJson(jsonResponse, User.class);
        mMainView.showParseJson(user);
    }
}
