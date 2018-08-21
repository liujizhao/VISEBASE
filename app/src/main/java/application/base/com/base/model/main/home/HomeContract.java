/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package application.base.com.base.model.main.home;

import com.androidlibrary.presenter.IBasePresenter;
import com.androidlibrary.presenter.IBaseView;

import java.util.List;

import application.base.com.base.AppBaseActivity;
import application.base.com.base.response.Banner;
import application.base.com.base.response.Module;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface HomeContract {

    interface IView extends IBaseView<IPresenter> {
        void bannerSuccess(List<Banner> user);

        void moduleSuccess(List<Module> user);
    }

    interface IPresenter extends IBasePresenter {
        void banner(AppBaseActivity activity);

        void module(AppBaseActivity activity);
    }
}