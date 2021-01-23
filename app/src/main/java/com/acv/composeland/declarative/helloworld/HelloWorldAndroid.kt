package com.acv.composeland.declarative.helloworld

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.acv.composeland.declarative.Text
import com.acv.composeland.declarative.interpreter.android
import com.acv.composeland.suspend.AndroidComposeView


class HelloWorldAndroid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorld(
                text = Text("Hello World")
            )
        }
    }
}

fun ComponentActivity.setContent(f: () -> HelloWorld) {
    val parent = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
    window.decorView.findViewById<ViewGroup>(android.R.id.content).run {
        if (getChildAt(0) as? AndroidComposeView == null) {
            window.decorView.findViewById<ViewGroup>(android.R.id.content).addView(parent)
        }
    }
    f().text.android(parent)
}