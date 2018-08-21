package application.base.com.base.utils

import android.app.Activity
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import application.base.com.base.R
import com.gyf.barlibrary.ImmersionBar

/**
 * 包名：com.piccfs.jiaanpei.util
 * 时间：2018/5/16 11:23
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author Blank
 */
internal object StatusBarUtil {

    fun initStatusBarConfig(activity: Activity, fragment: Fragment, titleView: View?) {
        if (ImmersionBar.hasNavigationBar(activity)) {
            addContentPadding(activity, titleView)
        } else {
            if (titleView == null) {
                ImmersionBar.with(activity, fragment).init()
            } else {
                ImmersionBar.with(activity, fragment).titleBar(titleView).init()
            }
        }
    }

    private fun addContentPadding(activity: Activity, titleView: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (titleView != null) {
                val contentView = window.decorView.findViewById<ViewGroup>(android.R.id.content)
                contentView.setPadding(0, ImmersionBar.getStatusBarHeight(activity), 0, 0)
                contentView.setBackgroundColor(ContextCompat.getColor(activity, R.color.main_color))
            }
        }
    }

    fun initStatusBarConfig(activity: Activity, titleView: View?) {
        if (ImmersionBar.hasNavigationBar(activity)) {
            addContentPadding(activity, titleView)
        } else {
            if (titleView == null) {
                ImmersionBar.with(activity).init()
            } else {
                ImmersionBar.with(activity).titleBar(titleView).init()
            }
        }
    }

    fun clearStatusBarConfig(activity: Activity) {
        ImmersionBar.with(activity).destroy()
    }

}
