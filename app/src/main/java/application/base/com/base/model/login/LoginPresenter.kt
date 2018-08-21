package application.base.com.base.model.login

import com.androidlibrary.http.ViseHttp
import com.vise.log.ViseLog

import application.base.com.base.AppBaseActivity
import application.base.com.base.http.BaseRequest
import application.base.com.base.http.BaseResponse
import application.base.com.base.http.HttpListener
import application.base.com.base.request.LoginRequest
import application.base.com.base.response.User
import application.base.com.base.utils.UIUtils

/**
 * Author Blank
 * Create on 2018/8/1 13:50
 * Description:
 */
class LoginPresenter(private val mLoginView: LoginContract.IView) : LoginContract.IPresenter {

    init {
        mLoginView.setPresenter(this)
    }

    override fun login(activity: AppBaseActivity, userName: String, password: String) {

        val baseRequest = BaseRequest<LoginRequest>()

        baseRequest.setRequestType("01")
        val loginRequest = LoginRequest()
        loginRequest.userName = userName
        loginRequest.userPassWord = password
        baseRequest.setRequestBaseInfo(loginRequest)

        ViseHttp.POST("appinterface.jspx").setBody(baseRequest).request(object : HttpListener<BaseResponse<User>>(activity) {

            override fun onComplete(response: BaseResponse<User>) {
                ViseLog.d(response.info)
                mLoginView.loginSuccess(response.info)
            }

            override fun onFail(errCode: Int, errMsg: String) {
                super.onFail(errCode, errMsg)
                UIUtils.toast(activity, errMsg)
            }
        })
    }

}
