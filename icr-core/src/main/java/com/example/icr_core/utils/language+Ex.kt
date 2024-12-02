package com.example.icr_core.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

fun Context.setAppLocale(language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    val resources = this.resources
    resources.updateConfiguration(config, resources.displayMetrics)
}