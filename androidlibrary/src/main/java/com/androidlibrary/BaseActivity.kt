package com.androidlibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBefore()
        setContentView(layoutId)
        initView()
        initData()
    }

    protected abstract fun initBefore()

    protected abstract fun initData()

    protected abstract fun initView()
}
