package application.base.com.base.model.main.home;

import android.support.annotation.NonNull;

import java.util.List;

import application.base.com.base.AppBaseActivity;
import application.base.com.base.response.Banner;
import application.base.com.base.response.Module;

/**
 * Author Blank
 * Create on 2018/8/1 13:50
 * Description:
 */
public class HomePresenter implements HomeContract.IPresenter {

    @NonNull
    private final HomeContract.IView mHomeView;

    public HomePresenter(HomeContract.IView view) {
        mHomeView = view;
        mHomeView.setPresenter(this);
    }

    @Override
    public void banner(AppBaseActivity activity) {

    }

    @Override
    public void module(AppBaseActivity activity) {

    }
}
