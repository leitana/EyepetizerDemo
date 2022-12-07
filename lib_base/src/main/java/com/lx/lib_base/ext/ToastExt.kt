package com.lx.lib_base.ext

import android.graphics.drawable.Drawable
import android.widget.Toast
import com.lx.lib_base.utils.AppGlobal
import es.dmoral.toasty.Toasty


fun toastError(msg: String) {
    AppGlobal.get()
        ?.let { Toasty.error(it, msg, Toast.LENGTH_SHORT, true).show() }
}

fun toastSucess(msg: String) {
    AppGlobal.get()
        ?.let { Toasty.success(it, msg, Toast.LENGTH_SHORT, true).show() }
}

fun toastInfo(msg: String) {
    AppGlobal.get()
        ?.let { Toasty.info(it, msg, Toast.LENGTH_SHORT, true).show() }
}

fun toastWarning(msg: String) {
    AppGlobal.get()
        ?.let { Toasty.warning(it, msg, Toast.LENGTH_SHORT, true).show() }
}

fun toastNormal(msg: String) {
    AppGlobal.get()
        ?.let { Toasty.normal(it, msg).show() }
}

fun toastWithIcon(msg: String, icon: Drawable) {
    AppGlobal.get()
        ?.let { Toasty.normal(it, msg, icon).show() }
}