package application.base.com.base.model.login

import android.os.Bundle
import application.base.com.base.AppBaseActivity
import application.base.com.base.LOGIN_RESULT
import application.base.com.base.R
import application.base.com.base.dao.AppDatabase
import application.base.com.base.response.User
import application.base.com.base.utils.UIUtils
import kotlinx.android.synthetic.main.login_activity.*

/**
 * Author Blank
 * Create on 2018/7/31 15:27
 * Description:
 */
class LoginActivity : AppBaseActivity(), LoginContract.IView {

    private var mPresenter: LoginContract.IPresenter? = null

    override val layoutId: Int
        get() = R.layout.login_activity

    override fun initBefore() {
        super.initBefore()
        val user = AppDatabase.getInstance(this).userDao().user
        if (user != null) {
            val bundle = Bundle()
            UIUtils.mainActivity(this, bundle)
            finish()
        }
    }

    override fun initData() {
        mPresenter = LoginPresenter(this)
    }

    override fun initView() {
        login.setOnClickListener {
            mPresenter!!.login(this, "18618432913", "123456")
        }
    }

    override fun setPresenter(presenter: LoginContract.IPresenter) {
        mPresenter = presenter
    }

    override fun loginSuccess(user: User) {
        val bundle = Bundle()
        bundle.putSerializable(LOGIN_RESULT, user)
        UIUtils.mainActivity(this, bundle)
        finish()
    }

}
