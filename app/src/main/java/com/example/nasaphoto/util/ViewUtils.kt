package com.example.nasaphoto.util

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.displayShimmer() {
    this.visibility = View.VISIBLE
    this.startShimmer()
}

fun ShimmerFrameLayout.clearShimmer() {
    this.visibility = View.GONE
    this.stopShimmer()
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
