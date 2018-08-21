package com.androidlibrary


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Author Blank
 * Create on 2018/7/31 15:41
 * Description:
 */
abstract class BaseFragment : Fragment() {

    private var mFragmentView: View? = null

    protected abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentView = LayoutInflater.from(activity).inflate(layoutId, container, false)
        return mFragmentView
    }

    protected abstract fun initView()

    protected abstract fun initData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }
}
