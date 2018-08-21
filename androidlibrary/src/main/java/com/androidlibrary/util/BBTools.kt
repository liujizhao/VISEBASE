package com.androidlibrary.util

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.File

/**
 * Created by Mafg on 2014/12/29.
 */
object BBTools {

    /**
     * 动态设置listView的高度
     * count 总条目
     */
    fun setListViewHeight(listView: ListView, adapter: BaseAdapter,
                          count: Int) {
        var totalHeight = 0
        for (i in 0 until count) {
            val listItem = adapter.getView(i, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * count
        listView.layoutParams = params
    }

    fun setListViewHeight(listView: ExpandableListView) {
        val listAdapter = listView.adapter
        var totalHeight = 0
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }

        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
        listView.requestLayout()
    }


    fun setViewLayoutParam(view: View, width_scale: Double, height_scale: Double) {
        when {
            view.parent is LinearLayout -> {
                val layoutParams = LinearLayout.LayoutParams((DeviceUtils.screenWidth * width_scale).toInt(), (DeviceUtils.screenWidth * height_scale).toInt())
                view.layoutParams = layoutParams
            }
            view.parent is RelativeLayout -> {
                val layoutParams = RelativeLayout.LayoutParams((DeviceUtils.screenWidth * width_scale).toInt(), (DeviceUtils.screenWidth * height_scale).toInt())
                view.layoutParams = layoutParams
            }
            view is RelativeLayout -> {
                val layoutParams = LinearLayout.LayoutParams((DeviceUtils.screenWidth * width_scale).toInt(), (DeviceUtils.screenWidth * height_scale).toInt())
                view.setLayoutParams(layoutParams)
            }
        }
    }

    fun formatEveryOneLayoutParam(view: View, width: Int, height: Int, size: Int) {
        if (view.parent is LinearLayout || view is LinearLayout) {
            val layoutParams = LinearLayout.LayoutParams(DeviceUtils.screenWidth / size, (DeviceUtils.screenWidth / size * (height / width.toDouble())).toInt())
            view.layoutParams = layoutParams
        } else if (view.parent is RelativeLayout || view is RelativeLayout) {
            val layoutParams = RelativeLayout.LayoutParams(DeviceUtils.screenWidth / size, (DeviceUtils.screenWidth / size * (height / width.toDouble())).toInt())
            view.layoutParams = layoutParams
        } else {
            try {
                val layoutParams = ViewGroup.LayoutParams(DeviceUtils.screenWidth / size, (DeviceUtils.screenWidth / size * (height / width.toDouble())).toInt())
                view.layoutParams = layoutParams
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun formatEveryOneLayoutParam(view: View, width: Int, height: Int, size: Int, leftPadding: Int, topPadding: Int, rightPadding: Int, bottomPadding: Int) {
        if (view.parent is LinearLayout || view is LinearLayout) {
            val layoutParams = LinearLayout.LayoutParams(DeviceUtils.screenWidth / size, (DeviceUtils.screenWidth / size * (height / width.toDouble())).toInt())
            view.layoutParams = layoutParams
        } else if (view.parent is RelativeLayout || view is RelativeLayout) {
            val layoutParams = RelativeLayout.LayoutParams(DeviceUtils.screenWidth / size, (DeviceUtils.screenWidth / size * (height / width.toDouble())).toInt())
            view.layoutParams = layoutParams
        } else {
            try {
                val layoutParams = ViewGroup.LayoutParams(DeviceUtils.screenWidth / size, (DeviceUtils.screenWidth / size * (height / width.toDouble())).toInt())
                view.layoutParams = layoutParams
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        view.setPadding((leftPadding / 720.0 * DeviceUtils.screenWidth).toInt(), (topPadding / 720.0 * DeviceUtils.screenWidth).toInt(), (rightPadding / 720.0 * DeviceUtils.screenWidth).toInt(), (bottomPadding / 720.0 * DeviceUtils.screenWidth).toInt())
    }

    /**
     * 首页每行宽度
     *
     * @param view
     * @param width
     * @param height
     * @param size
     */
    fun formatLineParentView(view: View, width: Int, height: Int, size: Int) {
        if (view.parent is LinearLayout || view is LinearLayout) {
            val layoutParams = LinearLayout.LayoutParams(DeviceUtils.screenWidth, (DeviceUtils.screenWidth / size * height / width.toDouble()).toInt())
            view.layoutParams = layoutParams
        } else if (view.parent is RelativeLayout || view is RelativeLayout) {
            val layoutParams = RelativeLayout.LayoutParams(DeviceUtils.screenWidth, (DeviceUtils.screenWidth / size * height / width.toDouble()).toInt())
            view.layoutParams = layoutParams
        }
    }


    /**
     * 从Uri获得图片真实路径
     *
     * @author mafg
     * @DATE 2015年1月7日
     */
    fun getRealPathFromURI(uri: Uri, resolver: ContentResolver): String {

        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = resolver.query(uri, projection, null, null, null)
        val columnIndex = cursor!!
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val str = cursor.getString(columnIndex)
        cursor.close()
        return str
    }

    //取得文件夹大小
    @Throws(Exception::class)
    fun getFileSize(file: File): Double {
        //判断文件是否存在
        return if (file.exists()) {
            //如果是目录则递归计算其内容的总大小，如果是文件则直接返回其大小
            if (!file.isFile) {
                //获取文件大小
                val fl = file.listFiles()
                var ss = 0.0
                for (f in fl)
                    ss += getFileSize(f)
                ss
            } else {
                file.length().toDouble() / 1024
            }
        } else {
            0.0
        }
    }

    /**
     * 验证手机号码是否符合要求
     */

    fun isLegal(phone: String): Boolean {
        val reg = "1[0-9]{10}"
        return phone.matches(reg.toRegex())
    }

    fun getWidthBy720(width: Int): Int {
        return (width / 720.0 * DeviceUtils.screenWidth).toInt()
    }

    fun setViewLayoutParam720(view: View, width: Int, height: Int) {
        when {
            view.parent is LinearLayout -> {
                val layoutParams = LinearLayout.LayoutParams((DeviceUtils.screenWidth * width / 720.0).toInt(), (DeviceUtils.screenWidth * height / 720.0).toInt())
                view.layoutParams = layoutParams
            }
            view.parent is RelativeLayout -> {
                val layoutParams = RelativeLayout.LayoutParams((DeviceUtils.screenWidth * width / 720.0).toInt(), (DeviceUtils.screenWidth * height / 720.0).toInt())
                view.layoutParams = layoutParams
            }
            view is RelativeLayout -> {
                val layoutParams = LinearLayout.LayoutParams((DeviceUtils.screenWidth * width / 720.0).toInt(), (DeviceUtils.screenWidth * height / 720.0).toInt())
                view.setLayoutParams(layoutParams)
            }
            else -> try {
                val layoutParams = ViewGroup.LayoutParams((DeviceUtils.screenWidth * width / 720.0).toInt(), (DeviceUtils.screenWidth * height / 720.0).toInt())
                view.layoutParams = layoutParams
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 设置全屏宽度的
     *
     * @param view
     * @param width
     * @param height
     */
    fun formatLayoutParam(view: View, width: Int, height: Int) {
        if (view.parent is LinearLayout || view is LinearLayout) {
            val layoutParams = LinearLayout.LayoutParams(DeviceUtils.screenWidth, (DeviceUtils.screenWidth * (height / width.toDouble())).toInt())
            view.layoutParams = layoutParams
        } else if (view.parent is RelativeLayout || view is RelativeLayout) {
            val layoutParams = RelativeLayout.LayoutParams(DeviceUtils.screenWidth, (DeviceUtils.screenWidth * (height / width.toDouble())).toInt())
            view.layoutParams = layoutParams
        } else {
            try {
                val layoutParams = ViewGroup.LayoutParams(DeviceUtils.screenWidth, (DeviceUtils.screenWidth * (height / width.toDouble())).toInt())
                view.layoutParams = layoutParams
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}
