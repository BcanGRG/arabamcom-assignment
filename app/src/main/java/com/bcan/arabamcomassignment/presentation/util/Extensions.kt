package com.bcan.arabamcomassignment.presentation.util

fun String?.formatImageResolution(width: Int = 800, height: Int = 600): String? {
    return this?.replace("{0}", "${width}x${height}")
}