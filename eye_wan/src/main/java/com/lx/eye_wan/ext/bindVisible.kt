package com.lx.eye_wan.ext

import android.graphics.drawable.Drawable
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.lx.eye_wan.R

/**
 * @title：BinderAdapters
 * @projectName LinWanAndroid-mvvm
 * @description: <Description>
 * @author linxiao
 * @data Created in 2021/04/15
 */

@BindingAdapter("visibleUnless")
fun bindVisible(view: View, visible: Boolean){
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

@BindingAdapter("goneUnless")
fun bindGoneUnless(view: View, gone: Boolean){
    view.visibility = if (gone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("htmlText")
fun bindHtmlText(textView: TextView, html: String){
    textView.text = if (fromN()) Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY) else Html.fromHtml(html)
}

@BindingAdapter("articleStar")
fun bindArticleStar(view:ImageView,collect:Boolean){
    view.setImageResource(if (collect) R.drawable.wan_ic_like else R.drawable.wan_ic_like_not)
}

@BindingAdapter("iconImage")
fun bindImage(view: ImageView, icon: Int){
    view.setImageResource(icon)
}

@BindingAdapter(
    "imageUrl",
    "imagePlaceholder",
    "circleCropImage",
    "crossFadeImage",
    "overrideImageWidth",
    "overrideImageHeight",
    "imageLoadListener",
    requireAll = false
)
fun bindImage(
    imageView: ImageView,
    imageUrl: String?,
    placeholder: Int? = null,
    circleCrop: Boolean? = false,
    crossFade: Boolean? = false,
    overrideWidth: Int? = null,
    overrideHeight: Int? = null,
    listener: RequestListener<Drawable>?
) {
    if (imageUrl == null) return
    var request = Glide.with(imageView.context).load(imageUrl)
    if (placeholder != null) {
        request = request.placeholder(placeholder)
    }
    if (circleCrop == true) {
        request = request.circleCrop()
    }
    if (crossFade == true) {
        request = request.transition(DrawableTransitionOptions.withCrossFade())
    }
    if (overrideWidth != null && overrideHeight != null) {
        request = request.override(overrideWidth, overrideHeight)
    }
    if (listener != null) {
        request = request.listener(listener)
    }
    request.into(imageView)
}