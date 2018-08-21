package application.base.com.base.model.main.enquiry;

import android.support.annotation.NonNull;

/**
 * Author Blank
 * Create on 2018/8/16 11:04
 * Description:
 */
public class EnquiryPresenter implements EnquiryContract.IPresenter {

    @NonNull
    private final EnquiryContract.IView mEnquiryView;

    public EnquiryPresenter(EnquiryContract.IView view) {
        mEnquiryView = view;
        mEnquiryView.setPresenter(this);
    }
}
