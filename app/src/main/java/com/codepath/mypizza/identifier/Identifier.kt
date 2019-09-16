package com.codepath.mypizza.identifier

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.codepath.mypizza.extensions.isVisible
import com.codepath.mypizza.extensions.md5

class Identifier {

    fun attachView(v: View) {
        if (v.isVisible()) {
            when (v) {
                is TextView -> Log.println(Log.INFO, "View id", v.resources.getResourceName(v.id) + (v.text as? String
                        ?: "").md5())
                is ViewGroup -> {

                    Log.println(Log.INFO, "View id", v.resources.getResourceName(v.id))
                    for (i in 0 until v.childCount) {
                        val child = v.getChildAt(i)
                        attachView(child)
                    }

                }
                else -> Log.println(Log.INFO, "View id", v.resources.getResourceName(v.id))
            }
        } else {
            Log.println(Log.INFO, "View id", "View not visible, not attached")
        }

    }

}
