@file:Suppress("DEPRECATION")

package com.androidlibrary.util


import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.androidlibrary.App
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

object DeviceUtils {

    // 原始UI界面设计图的宽度(px)，用于后期对控件做缩放
    private const val UI_Design_Width = 720f
    const val UI_Design_Height = 1080f

    val screenWidth: Int
        get() {
            val dm = DisplayMetrics()
            val wm = App.instance!!
                    .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }

    private val screenHeight: Int
        get() {
            val dm = DisplayMetrics()
            val wm = App.instance!!
                    .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(dm)

            return dm.heightPixels
        }

    val screenWidthScale: Float
        get() = if (screenWidth < screenHeight) screenWidth / UI_Design_Width else screenHeight / UI_Design_Width

    val screenHeightScale: Float
        get() = if (screenWidth < screenHeight) screenHeight / UI_Design_Width else DeviceUtils.screenHeight / UI_Design_Width

    /**
     * 获取当前手机的密度
     *
     * @param activity
     * @return
     */
    val density: Float
        get() {
            val dm = DisplayMetrics()
            val windowManager = App.instance!!
                    .getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(dm)

            return dm.density
        }

    /**
     * 获取系统当前时间，精确到秒
     *
     * @return 返回当前时间字符串
     */
    val nowTime: String
        get() {
            @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("yyyyMMddHHmmss")
            return sdf.format(Calendar.getInstance().time)
        }

    /**
     * 获取系统版本
     * DeviceUtils.getSDKVersion()<BR></BR>
     * <P>Author : chenbo </P>
     * <P>Date : 2012-11-8 </P>
     *
     * @return string
     */
    val androidSDKVersion: String
        get() = Build.VERSION.RELEASE

    /**
     * 获取手机型号
     * DeviceUtils.getPhoneModel()<BR></BR>
     * <P>Author : chenbo </P>
     * <P>Date : 2012-11-8 </P>
     *
     * @return
     */
    val phoneModel: String
        get() = android.os.Build.MODEL

    /**
     * 获取手机品牌
     *
     * @return String 手机品牌
     */
    val brand: String
        get() = android.os.Build.BRAND

    /**
     * 获取手机号
     * DeviceUtils.getPhoneNumber()<BR></BR>
     * <P>Author : chenbo </P>
     * <P>Date : 2012-11-8 </P>
     *
     * @return
     */
    val phoneNumber: String
        get() {
            val tm = App.instance!!
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm.line1Number
        }

    private val deviceSerial: String
        @SuppressLint("PrivateApi")
        get() {
            var serial = ""
            try {
                val c = Class.forName("android.os.SystemProperties")
                val get = c.getMethod("get", String::class.java)
                serial = get.invoke(c, "ro.serialno") as String
            } catch (ignored: Exception) {
            }

            return serial
        }

    val deviceID: String
        get() {
            val context = App.instance

            val tm = context!!
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            val imei = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tm.imei
            } else {
                TODO("VERSION.SDK_INT < O")
//                tm.deviceId
            }

            val androidID = android.provider.Settings.System.getString(
                    context.contentResolver, "android_id")

            val serialNo = deviceSerial

            return imei + androidID + serialNo
        }

    /**
     * 获取当前操作系统的语言
     *
     * @return String 系统语言
     */
    val sysLanguage: String
        get() = Locale.getDefault().language

    /**
     * 得到手机MAC地址
     *
     * @param context 上下文对象
     * @return
     */
    val localMacAddress: String
        get() {
            val wifi = App.instance!!.applicationContext
                    .getSystemService(Context.WIFI_SERVICE) as WifiManager
            val info = wifi.connectionInfo
            return info.macAddress
        }

    /**
     * 获取GUID(唯一标示符) 不带"-"的32位字符
     * StrUtils.getGUID()<BR></BR>
     * <P>Author : chenxiangming </P>
     * <P>Date : 2012-12-13 </P>
     *
     * @return
     */
    val guid: String
        get() {
            val uuidRaw = UUID.randomUUID().toString()
            return uuidRaw.replace("-".toRegex(), "")
        }

    val statusBarHeight: Int
        get() {
            val activity = ActivityStack.getInstance().currentActivity()
            val frame = Rect()
            activity!!.window.decorView.getWindowVisibleDisplayFrame(frame)
            return frame.top
        }

    val channelId: String
        get() {
            var channelId: String? = "-"
            try {
                channelId = App.instance!!.packageManager.getApplicationInfo(
                        App.instance!!.packageName, PackageManager.GET_META_DATA).metaData.getString("UMENG_CHANNEL")
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return channelId!!
        }

    // 将流转化为字符串
    val localIpAddress: String
        get() {
            var ip = "unknown"
            try {
                val address = "http://ip.taobao.com/service/getIpInfo2.php?ip=myip"
                val url = URL(address)
                val connection = url
                        .openConnection() as HttpURLConnection
                connection.useCaches = false

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val `in` = connection.inputStream
                    val reader = BufferedReader(
                            InputStreamReader(`in`))

                    var tmpString: String
                    val retJSON = StringBuilder()
                    do {
                        tmpString = reader.readLine()
                        if (tmpString == null)
                            break
                        retJSON.append(tmpString + "\n")
                    } while (true)

                    val jsonObject = JSONObject(retJSON.toString())
                    val code = jsonObject.getString("code")
                    if (code == "0") {
                        val data = jsonObject.getJSONObject("data")
                        ip = data.getString("ip")

                        Log.e("提示", "您的IP地址是：$ip")
                    } else {
                        Log.e("提示", "IP接口异常，无法获取IP地址！")
                    }
                } else {
                    Log.e("提示", "网络连接异常，无法获取IP地址！")
                }
            } catch (e: Exception) {
                Log.e("提示", "获取IP地址时出现异常，异常信息是：" + e.toString())
            }

            return ip
        }

    /**
     * 获取屏幕尺寸
     * USystem.getScreenSize()<BR></BR>
     * <P>Author : chenxiangming </P>
     * <P>Date : 2013-3-14 </P>
     *
     * @param context
     * @return
     */
    fun getScreenSize(context: Context): String {
        val dm = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(dm)
        val dia = Math.sqrt(Math.pow(dm.widthPixels.toDouble(), 2.0) + Math.pow(dm.heightPixels.toDouble(), 2.0))
        val screenInches = dia / (160 * dm.density)
        return String.format("%.1f", screenInches)
    }

    /**
     * 获取运营商信息
     *
     * @param context 上下文
     * @return String 运营商信息
     */
    fun getCarrier(context: Context): String {
        val telManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val imsi = telManager.subscriberId
        if (imsi != null && "" != imsi) {
            // 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
            if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
                return "中国移动"
            } else if (imsi.startsWith("46001")) {
                return "中国联通"
            } else if (imsi.startsWith("46003")) {
                return "中国电信"
            }
        }
        return ""
    }

    /**
     * 获取网络类型
     * USystem.getNetType()<BR></BR>
     * <P>Author : chenxiangming </P>
     * <P>Date : 2013-3-14 </P>
     *
     * @return
     */
    fun getNetType(context: Context): String {
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val gprs = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifi != null && wifi.state == NetworkInfo.State.CONNECTED) {
            return "WIFI"
        } else if (gprs != null && gprs.state == NetworkInfo.State.CONNECTED) {
            return "3G/GPRS"
        }
        return ""
    }

    /**
     * 读取sim卡序列号  IMSI
     */
    fun readSimSerialNum(): String {
        var telephonyManager: TelephonyManager? = null
        if (telephonyManager == null) {
            telephonyManager = App.instance!!
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        }
        return telephonyManager.simSerialNumber
    }

    /**
     * 读取手机串号  IMEI
     *
     * @param con 上下文
     * @return String 手机串号
     */
    fun readTelephoneSerialNum(): String {
        val telephonyManager = App.instance!!
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.deviceId
    }

    /**
     * 读取imei
     */
    fun getImei(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.deviceId
    }

}
