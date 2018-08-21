package application.base.com.base.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import application.base.com.base.*
import application.base.com.base.model.login.LoginActivity
import application.base.com.base.model.main.MainActivity

/**
 * Author Blank
 * Create on 2018/7/31 15:44
 * Description:
 */
object UIUtils {

    fun toast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun mainActivity(context: Context, bundle: Bundle) {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(MAIN_BUNDLE, bundle)
        context.startActivity(intent)
    }

    fun loginActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}
