package application.base.com.base.model.main.enquiry;

import application.base.com.base.BaseAppFragment;
import application.base.com.base.R;

/**
 * Author Blank
 * Create on 2018/7/31 15:42
 * Description:
 */
public class EnquiryFragment extends BaseAppFragment implements EnquiryContract.IView {

    private EnquiryContract.IPresenter mEnquiryPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.enquiry_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void setPresenter(EnquiryContract.IPresenter presenter) {
        mEnquiryPresenter = presenter;
    }
}
