package com.lx.lib_base.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.lx.lib_base.R
import es.dmoral.toasty.Toasty
import kotlin.reflect.KClass

/**
 * @title：Ext
 * @projectName Szt
 * @description: <Description>
 * @author linxiao
 * @data Created in 2021/05/06
 */
fun <T : Activity> Activity.startActivity(clazz: KClass<T>, block: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, clazz.java).apply {
        block?.invoke(this)
    }
    startActivity(intent)
}

fun <T : Activity> Fragment.startActivity(clazz: KClass<T>, block: (Intent.() -> Unit)? = null) {
    val intent = Intent(activity, clazz.java).apply {
        block?.invoke(this)
    }
    startActivity(intent)
}

fun Fragment.showToast(string: String){
    Toast.makeText(this.activity, string, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Activity.showSnackMsg(msg: String){
    val snackbar = Snackbar.make(this.window.decorView, msg, Snackbar.LENGTH_SHORT)
    val view = snackbar.view
    view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(ContextCompat.getColor(this, R.color.white))
    snackbar.show()
}

fun Fragment.showSnackMsg(msg: String) {
    this.activity ?: return
    val snackbar = Snackbar.make(this.requireActivity().window.decorView, msg, Snackbar.LENGTH_SHORT)
    val view = snackbar.view
    view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(ContextCompat.getColor(this.requireActivity(), R.color.white))
    snackbar.show()
}

fun Context.toastError(msg: String) {
    Toasty.error(this, msg, Toast.LENGTH_SHORT, true).show()
}

fun Context.toastSucess(msg: String) {
    Toasty.success(this, msg, Toast.LENGTH_SHORT, true).show()
}

fun Context.toastInfo(msg: String) {
    Toasty.info(this, msg, Toast.LENGTH_SHORT, true).show()
}

fun Context.toastWarning(msg: String) {
    Toasty.warning(this, msg, Toast.LENGTH_SHORT, true).show()
}

fun Context.toastNormal(msg: String) {
    Toasty.normal(this, msg).show()
}

fun Context.toastWithIcon(msg: String, icon: Drawable) {
    Toasty.normal(this, msg, icon).show();
}
