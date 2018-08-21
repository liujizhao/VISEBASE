package application.base.com.base

import android.app.AlertDialog
import com.androidlibrary.BaseActivity
import com.androidlibrary.util.ActivityStack
import com.vise.log.ViseLog

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
abstract class AppBaseActivity : BaseActivity() {

    var baseActivity: AppBaseActivity? = null
    /**
     *
     */

    private var mAlertDialog: AlertDialog? = null

    override fun initBefore() {
        baseActivity = this
        ActivityStack.getInstance().pushActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityStack.getInstance().popActivity(this)
    }

    fun showDialog() {
        if (mAlertDialog == null) {
            mAlertDialog = AlertDialog.Builder(ActivityStack.getInstance().cActivity).setMessage("load").create()
        }
        if (!mAlertDialog!!.isShowing) {
            ViseLog.d("showDialog")
            mAlertDialog!!.show()
        }
    }

    fun dismissDialog() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            ViseLog.d("dismissDialog")
            mAlertDialog!!.dismiss()
        }
    }

}
