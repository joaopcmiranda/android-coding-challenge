package com.codepath.mypizza.identifier

import android.util.Log
import android.view.View
import android.widget.TextView
import com.codepath.mypizza.extensions.isVisible
import com.codepath.mypizza.extensions.md5


class Identifier {

    fun attachView(v: View) {
        if (v.isVisible()) {
            if (v is TextView) {
                Log.println(Log.INFO, "View visible", v.resources.getResourceName(v.id) + (v.text as? String
                        ?: "").md5())
            } else {
                Log.println(Log.INFO, "View visible", v.resources.getResourceName(v.id))
            }
        } else {
            Log.println(Log.WARN, "lol", ":(")

        }

    }

}
