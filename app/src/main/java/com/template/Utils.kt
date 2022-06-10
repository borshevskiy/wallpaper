package com.template

import android.annotation.SuppressLint
import android.app.Activity
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val WALLPAPERS = "wallpapers"
const val NO_NAME = "noname"
const val DIALOG = "dialog"
const val EXIT_TEXT = "After 1 second you will exit the application"
const val SET_WALLPAPERS_TEXT = "Wallpapers have been installed"

@SuppressLint("ClickableViewAccessibility")
fun ViewGroup.closeThreeFingersTouch(activity: Activity, boolean: Boolean) {
    this.setOnTouchListener { _, event ->
        if (event.pointerCount == 3) {
            Toast.makeText(activity, EXIT_TEXT, Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                activity.finish()
            }
        }
        boolean
    }
}