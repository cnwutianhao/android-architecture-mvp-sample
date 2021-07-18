package com.tyhoo.mvp.java.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tyhoo.mvp.java.R;
import com.tyhoo.mvp.java.data.User;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainFragment extends Fragment implements MainContract.View {

    private static final String TAG = MainFragment.class.getSimpleName();

    private MainContract.Presenter mMainPresenter;

    private TextView mUserText;
    private ProgressBar mUserPb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mUserText = view.findViewById(R.id.user_text);
        mUserPb = view.findViewById(R.id.user_pb);
        Button parseJsonBtn = view.findViewById(R.id.parse_json_btn);

        parseJsonBtn.setOnClickListener(v -> {
            Log.d(TAG, "Parse Json.");
            mMainPresenter.parseJson();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainPresenter.start();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mMainPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            mUserPb.setVisibility(View.VISIBLE);
        } else {
            mUserPb.setVisibility(View.GONE);
        }
    }

    @Override
    public void showUser(String user) {
        mUserText.setVisibility(View.VISIBLE);
        mUserText.setText(user);
    }

    @Override
    public void hideUser() {
        mUserText.setVisibility(View.GONE);
    }

    @Override
    public void showParseJson(User user) {
        String text = getString(R.string.parse_text,
                user.getLogin(), user.getHtmlUrl(), user.getName(),
                user.getCompany(), user.getLocation());
        mUserText.setText(text);
    }
}
