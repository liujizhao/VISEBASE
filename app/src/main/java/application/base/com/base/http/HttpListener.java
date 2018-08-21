package application.base.com.base.http;

import com.androidlibrary.http.callback.ACallback;
import com.vise.log.ViseLog;

import application.base.com.base.AppBaseActivity;

/**
 * 包名：application.base.com.base.response
 * 时间：2018/8/17
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author liujizhao
 */
public abstract class HttpListener<T> extends ACallback<BaseResponse> {

    private AppBaseActivity sBaseActivity;

    public HttpListener(AppBaseActivity appBaseActivity) {
        appBaseActivity.showDialog();
        sBaseActivity = appBaseActivity;
    }

    public abstract void onComplete(T response);

    @Override
    public void onSuccess(BaseResponse data) {
        sBaseActivity.dismissDialog();
        if (data.isSuccess() && null != data.getInfo()) {
            onComplete((T) data);
        } else {
            onFail(-1, data.getHead().errMsg);
        }
    }

    @Override
    public void onFail(int errCode, String errMsg) {
        sBaseActivity.dismissDialog();
        ViseLog.e(errCode + "---" + errMsg);
    }
}
