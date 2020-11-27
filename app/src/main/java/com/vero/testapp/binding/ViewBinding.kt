package com.vero.testapp.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.vero.testapp.ext.gone

@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean) {
    view.gone(shouldBeGone)
}
