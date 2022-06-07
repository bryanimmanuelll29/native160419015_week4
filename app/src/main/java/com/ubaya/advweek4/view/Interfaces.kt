package com.ubaya.advweek4.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.advweek4.model.Student

interface ButtonDetailClickListener {
    fun onButtonDetailClick(v: View)
}

interface ButtonNotificationClickListener {
    fun onButtonNotification(v: View)
}