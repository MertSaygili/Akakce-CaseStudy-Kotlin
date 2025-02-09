package com.example.akakcecasestudykotlin.utils.extensions

import android.content.res.Resources

class SizeConverterExtension {
    companion object {
        fun Int.dpToPx(): Int {
            return (this * Resources.getSystem().displayMetrics.density).toInt()
        }
    }
}