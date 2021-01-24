package com.acv.composeland.suspend

import android.content.Context
import android.widget.LinearLayout

internal class AndroidComposeView(context: Context) : LinearLayout(context) {
    init {
        orientation = VERTICAL
    }

    fun clean(){
        removeAllViews()
    }
}
