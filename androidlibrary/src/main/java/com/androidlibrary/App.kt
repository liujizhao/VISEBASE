package com.androidlibrary

import android.app.Application
import android.util.Log
import com.vise.log.ViseLog
import com.vise.log.inner.LogcatTree

/**
 * Author Blank
 * Create on 2018/8/15 15:18
 * Description:
 */
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        ViseLog.getLogConfig()
                .configAllowLog(true)//是否输出日志
                .configShowBorders(true)//是否排版显示
                .configTagPrefix("ViseLog")//设置标签前缀
//                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")//个性化设置标签，默认显示包名
                .configLevel(Log.DEBUG)//设置日志最小输出级别，默认Log.VERBOSE
        ViseLog.plant(LogcatTree())//添加打印日志信息到Logcat的树
    }

    companion object {

        var instance: App? = null
            private set
    }

}
