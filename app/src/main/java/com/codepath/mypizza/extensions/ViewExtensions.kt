package com.codepath.mypizza.extensions

import android.graphics.Point
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver


fun View.isVisible(): Boolean {

    if (!this.isShown) {
        return false
    }
    Log.println(Log.DEBUG, "dev", "shown")
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    val actualPosition = Rect(location[0], location[1], location[0] + this.width, location[1] + this.height)

    val display = this.display
    val size = Point()
    display.getSize(size)
    val width = size.x
    val height = size.y

    Log.println(Log.DEBUG, "dev", actualPosition.toString())

    val screen = Rect(0, 0, width, height)
    Log.println(Log.DEBUG, "dev", screen.toString())
    return actualPosition.intersect(screen)
}

fun View.onLayoutChanged(callback: (View) -> Unit) {
    val _this = this
    this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            _this.viewTreeObserver.removeOnGlobalLayoutListener(this)
            callback(_this)
        }
    })
}