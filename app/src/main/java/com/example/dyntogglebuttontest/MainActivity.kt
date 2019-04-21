package com.example.dyntogglebuttontest

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.res.ColorStateListInflaterCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.Xml
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ToggleButton
import com.google.android.flexbox.FlexboxLayout
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity() {

    private var add_edit: EditText? = null
    private var add_button: Button? = null
    private var del_button: Button? = null
    private var flex_layout: FlexboxLayout? = null

    private var toggle_items: MutableList<ToggleButton> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initChildViews()
    }

    private fun initChildViews() {
        flex_layout = findViewById(R.id.flexbox_layout)
        add_edit = findViewById(R.id.edit_add)
        add_button = findViewById(R.id.button_add)
        add_button?.setOnClickListener(View.OnClickListener {
            var text = add_edit?.text.toString()

            if (text.length == 0) return@OnClickListener

            var toggle = createChildToggleButton(text)
            toggle_items.add(toggle)

            flex_layout?.addView(toggle)
            add_edit?.text?.clear()
        })
        del_button = findViewById(R.id.button_del)
        del_button?.setOnClickListener(View.OnClickListener {
            var delete_items: MutableList<ToggleButton> = mutableListOf()
            for (toggle_item in toggle_items) {
                if (!toggle_item.isChecked) continue
                delete_items.add(toggle_item)
            }

            for (toggle_item in delete_items) {
                flex_layout?.removeView(toggle_item)
                toggle_items.remove(toggle_item)
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun createChildToggleButton(text: String) : ToggleButton {
        var toggle = ToggleButton(this)

        toggle.text = text
        toggle.textOn = text
        toggle.textOff = text

        var params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.topMargin = 3.dp
        params.bottomMargin = 3.dp
        params.leftMargin = 3.dp
        params.rightMargin = 3.dp
        toggle.layoutParams = params

        toggle.isAllCaps = false

        toggle.setPadding(10.dp, 0, 10.dp, 0)
        toggle.background = ResourcesCompat.getDrawable(resources, R.drawable.dyn_toggle_selector, null)

        var states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf()
        )

        var colors = intArrayOf(
            Color.WHITE,
            Color.BLUE
        )

        var list = ColorStateList(states, colors)

        toggle.setTextColor(list)
        toggle.minimumWidth = 0
        toggle.minWidth = 0
0
        return toggle
    }
}
