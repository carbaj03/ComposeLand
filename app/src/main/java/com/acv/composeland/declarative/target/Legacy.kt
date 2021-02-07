package com.acv.composeland.declarative.target.legacy

import android.graphics.Canvas

abstract class Context

class Window {
    private var decorView: ViewGroup? = null

    fun <A : View?> findViewById(id: Int): A? =
        decorView?.findViewById(id)

    fun setContentView(id: Int, context: Context) {
        decorView = Layout.inflate(id, context)
    }
}

object Layout {
    fun inflate(id: Int, context: Context): ViewGroup =
        Xml.get(id).parse(context)
}


object Xml {
    fun get(id: Int): XmlParser = XmlParser
}

object XmlParser {
    fun parse(context: Context): ViewGroup =
        FrameLayout(context, R.layout.cotent).apply {
            LinearLayout(context, R.id.linearLayout).apply {
                orientation = LinearLayout.Orientation.Vertical
                addView(TextView(context, R.id.textView))
                addView(ButtonView(context, R.id.button))
            }
        }
}

object R {
    object layout {
        const val cotent: Int = 10000001
        const val main: Int = 10000002
    }

    object id {
        const val linearLayout: Int = 10001001
        const val textView: Int = 10001002
        const val button: Int = 10001003
    }
}

/**
 * <p>
 * This class represents the basic building block for user interface components. A View
 * occupies a rectangular area on the screen and is responsible for drawing and
 * event handling. View is the base class for <em>widgets</em>, which are
 * used to create interactive UI components (buttons, text fields, etc.). The
 * {@link android.view.ViewGroup} subclass is the base class for <em>layouts</em>, which
 * are invisible containers that hold other Views (or other ViewGroups) and define
 * their layout properties.
 * </p>
 */
abstract class View(open val context: Context) {
    abstract val id: Int
    open fun <A : View?> findViewById(id: Int): A? {
        if (id == -1) return null

        return findViewTraversal(id)
    }

    protected open fun <A : View?> findViewTraversal(id: Int): A? {
        return if (id == -1) this as A
        else null
    }

    fun invalidate() {
        //redraw the view
    }

    fun draw(canvas: Canvas) {
        //paint logic
    }
}

/** A <code>ViewGroup</code> is a special view that can contain other views
 * (called children.) The view group is the base class for layouts and views
 * containers.
 * */
abstract class ViewGroup(context: Context) : View(context) {
    val children: MutableList<View> = mutableListOf()

    fun addView(view: View) = children.add(view)

    override fun <T : View?> findViewTraversal(id: Int): T? {
        if (id == this.id) {
            return this as T
        }
        var v: T?
        children.forEach {
            v = findViewById<T>(it.id)
            if (v != null)
                return v
        }
        return null
    }
}

data class FrameLayout(override val context: Context, override val id: Int) : ViewGroup(context)

data class LinearLayout(override val context: Context, override val id: Int) : ViewGroup(context) {
    enum class Orientation {
        Vertical, Horizontal
    }

    var orientation: Orientation = Orientation.Vertical
}

data class TextView(override val context: Context, override val id: Int) : View(context) {
    var text: String = ""
        set(value) {
            field = value
            invalidate()
        }
}

data class ButtonView(override val context: Context, override val id: Int) : View(context) {
    var onClick: () -> Unit = {}
    var text: String = ""
}

data class ImageView(override val context: Context, override val id: Int) : View(context) {
    var resource: Int = 0
}

class Activity : Context() {
    private var window: Window = Window()

    fun onCreate() {
        window.setContentView(R.layout.main, this)

        window.findViewById<TextView>(R.id.textView)?.text = "Hello"

        window.findViewById<ButtonView>(R.id.button)?.text = "Click Me"
        window.findViewById<ButtonView>(R.id.button)?.onClick = {
            window.findViewById<TextView>(R.id.textView)?.text = "Click"
        }
    }
}