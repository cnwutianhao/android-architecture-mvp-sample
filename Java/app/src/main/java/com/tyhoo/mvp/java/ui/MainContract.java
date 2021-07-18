package com.tyhoo.mvp.java.ui;

import com.tyhoo.mvp.java.base.BasePresenter;
import com.tyhoo.mvp.java.base.BaseView;
import com.tyhoo.mvp.java.data.User;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showUser(String user);

        void hideUser();

        void showParseJson(User user);
    }

    interface Presenter extends BasePresenter {

        void parseJson();
    }
}
