/**
 * Copyright 2013 lefeng.
 *
 *
 * Licensed under the Apache License and lefeng License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.lefeng.com
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidlibrary.util

/**
 * 自定义activity管理栈<br></br>
 * 满足dock栏activity返回定位功能<br></br>
 * <P>File name : ActivityStack.java </P>
 * <P>Author : mingli </P>
 * <P>Date : 2013-6-28 </P>
 */

import android.annotation.SuppressLint
import android.app.Activity
import java.util.*

class ActivityStack private constructor() {
    private var activityStack: Vector<Activity>? = null
    private var currentActivity: Activity? = null

    val cActivity: Activity
        get() = activityStack!![activityStack!!.size - 1]

    fun popActivity(activity: Activity?) {
        if (activity != null) {
            if (!activity.isFinishing) {
                activity.finish()
            }
            activityStack!!.remove(activity)
        }
    }

    fun popActivity(cls: Class<*>) {
        if (null == activityStack) {
            return
        }
        for (i in activityStack!!.indices) {
            val activity = activityStack!!.elementAt(i)
            if (cls == activity.javaClass) {
                popActivity(activity)
                break
            }
        }
    }

    fun popAllActivityExcept(vararg classes: Class<*>) {
        if (null == activityStack) {
            return
        }

        var i = 0
        while (i < activityStack!!.size) {
            val activity = activityStack!!.elementAt(i)

            if (classes.isNotEmpty()) {
                var hasActiviy = false
                for (cls in classes) {
                    if (cls == activity!!.javaClass) {
                        hasActiviy = true
                        break
                    }
                }

                if (hasActiviy) {
                    i++
                    continue
                }
            }
            if (null != activity) {
                i--
            }
            popActivity(activity)
            i++
        }
    }

    fun pushActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Vector()
        }
        currentActivity = activity
        activityStack!!.add(activity)
    }

    fun setCurrentActivity(currentActivity: Activity) {
        this.currentActivity = currentActivity
    }

    fun currentActivity(): Activity? {
        return currentActivity
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ActivityStack? = null

        fun getInstance(): ActivityStack {
            if (instance == null) {
                instance = ActivityStack()
            }
            return this.instance!!
        }
    }

}
