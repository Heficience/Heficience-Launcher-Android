package com.heficience.androidlauncher.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.TextClock
import androidx.annotation.RequiresApi
import java.lang.Exception


class Clock : TextClock {
    constructor(context: Context?) : super(context) {
        //
        setDesigningText()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        //
        setDesigningText()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        //
        setDesigningText()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        //
        setDesigningText()
    }

    private fun setDesigningText() {
        // The default text is displayed when designing the interface.
        this.text = "00:00:00"
    }

    override fun onAttachedToWindow() {
        try {
            super.onAttachedToWindow()
        } catch (e: Exception) {
        }
    }
}