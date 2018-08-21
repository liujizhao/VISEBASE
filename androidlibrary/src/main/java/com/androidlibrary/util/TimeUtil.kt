package com.androidlibrary.util

import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    /**
     * 判断是否快速双击
     *
     * @return
     */

    private var lastClickTime: Long? = null

    val dateToString: String
        get() {
            val time = Date().time


            val df = SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.CHINA)

            return df.format(time)

        }

    private val timesmorning: Long
        get() {
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.MILLISECOND, 0)
            return cal.timeInMillis
        }

    private val timesYesterday: Long
        get() {
            val cal = Calendar.getInstance()
            cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH - 1)
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.MILLISECOND, 0)
            return cal.timeInMillis
        }

    val isFastDoubleClick: Boolean
        get() {
            try {
                if (lastClickTime == null) {
                    lastClickTime = 0L
                }
            } catch (e: NumberFormatException) {
                lastClickTime = 0L
            }

            val time = System.currentTimeMillis()
            val dur = time - lastClickTime!!
            lastClickTime = time

            return dur < 2000
        }

    private fun getTimeMillis(server_time: String): Long {
        if (TextUtils.isEmpty(server_time)) {
            return 0
        }
        val time = 0L
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
        val d: Date
        try {
            d = sdf.parse(server_time)
            return d.time
        } catch (e: ParseException) {
            //  Auto-generated catch block
            e.printStackTrace()
        }

        return time
    }

    /**
     * 上次点击时间
     */

    fun timeFormat(server_time: String): String {
        if (TextUtils.isEmpty(server_time)) {
            return ""
        }
        val minute: Long = 60000
        val currCalendar = Calendar.getInstance()

        val serverTime = getTimeMillis(server_time)

        currCalendar.timeInMillis = serverTime

        val currTime = System.currentTimeMillis()
        currCalendar.timeInMillis = currTime


        val diffTime = currTime - serverTime + 10000
        if (diffTime < 0) {
            val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
            var time = sdf.format(Date(serverTime))
            if (time.contains("年0"))
                time = time.replace("年0", "年")
            if (time.contains("月0"))
                time = time.replace("月0", "月")
            if (time.contains("日 0"))
                time = time.replace("日 0", "日 ")
            return time
        } else if (diffTime in 1..(minute - 1)) {
            return "现在"
        } else if (diffTime >= minute && diffTime < minute * 60) {
            val sdf = SimpleDateFormat("mm分钟前", Locale.CHINA)
            var time = sdf.format(Date(diffTime))
            if (time.startsWith("0"))
                time = time.substring(1)
            return time
        } else if (diffTime >= minute * 60 && diffTime < minute * 60 * 24
                && serverTime >= timesmorning) {
            val sdf = SimpleDateFormat("今天 HH:mm", Locale.CHINA)
            var time = sdf.format(Date(serverTime))
            if (time.contains("今天 0"))
                time = time.replace("今天 0", "今天 ")
            return time
        } else if (diffTime >= minute * 60 && diffTime < minute * 60 * 24 * 2
                && serverTime < timesmorning
                && serverTime >= timesYesterday) {
            val sdf = SimpleDateFormat("昨天 HH:mm", Locale.CHINA)
            var time = sdf.format(Date(serverTime))
            if (time.contains("昨天 0"))
                time = time.replace("昨天 0", "昨天 ")
            return time
        } else {
            val year = SimpleDateFormat("yyyy", Locale.CHINA)
            if (year.format(Date(getTimeMillis(server_time))) == year.format(Date(currTime))) {
                val sdf = SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA)
/*if (time.startsWith("0"))
					time = time.substring(1);
				if (time.contains("月0"))
					time = time.replace("月0", "月");
				if (time.contains("日 0"))
					time = time.replace("日 0", "日 ");*/
                return sdf.format(Date(serverTime))
            }

            val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
/*if (time.contains("年0"))
				time = time.replace("年0", "年");
			if (time.contains("月0"))
				time = time.replace("月0", "月");
			if (time.contains("日 0"))
				time = time.replace("日 0", "日 ");*/
            return sdf.format(Date(serverTime))
        }
    }


    fun isFastDoubleClick(v: View?): Boolean {
        if (v == null) {
            return false
        }
        val lastClickTime: Long?
        lastClickTime = try {
            v.tag as Long
        } catch (e: NumberFormatException) {
            0L
        }

        val time = System.currentTimeMillis()
        val dur = time - lastClickTime

        if (dur < 1000) {
            return true
        }
        v.tag = time
        return false
    }


    fun getDateDays(date1: String, date2: String): Int {
        val sdf = SimpleDateFormat("yyyymmdd", Locale.CHINA)
        try {
            val date = sdf.parse(date1)// 通过日期格式的parse()方法将字符串转换成日期
            val dateBegin = sdf.parse(date2)
            var betweenTime = date.time - dateBegin.time
            betweenTime = betweenTime / 1000 / 60 / 60 / 24
            return betweenTime.toInt()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return 10000
    }

    fun cannoutDoubleClick(view: View) {
        view.isClickable = false
        object : Thread() {
            override fun run() {
                SystemClock.sleep(1500)
                view.isClickable = true
            }
        }.start()
    }

    fun cannoutDoubleClick(rbList: ArrayList<View>) {
        for (view in rbList) {
            view.isEnabled = false
        }

        object : Thread() {
            override fun run() {
                SystemClock.sleep(3000)
                for (view in rbList) {
                    view.isEnabled = true
                }
            }
        }.start()
    }


}
