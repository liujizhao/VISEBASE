package application.base.com.base


import android.content.Context

import com.androidlibrary.BaseFragment

/**
 * Author Blank
 * Create on 2018/7/31 15:41
 * Description:
 */
abstract class BaseAppFragment : BaseFragment() {
    var baseActivity: AppBaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        baseActivity = context as AppBaseActivity?
    }

    companion object {

        private val TAG = BaseAppFragment::class.java.simpleName
    }
}
