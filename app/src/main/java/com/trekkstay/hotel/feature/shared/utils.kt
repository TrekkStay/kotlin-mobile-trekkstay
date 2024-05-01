package com.trekkstay.hotel.feature.shared

import java.util.Locale

object Utils {
    fun formatPrice(price: Double): String {
        val formattedPrice = String.format(Locale.US, "%.2f", price)
        return if (formattedPrice.endsWith(".00")) {
            formattedPrice.substring(0, formattedPrice.length - 3)
        } else if (formattedPrice.endsWith("0")) {
            formattedPrice.substring(0, formattedPrice.length - 1)
        } else {
            formattedPrice
        }
    }

    fun labelizeRating(rating: Int): String {
        when (rating) {
            1 -> return "Terrible"
            2 -> return "Bad"
            3 -> return "Okay"
            4 -> return "Good"
            5 -> return "Excellent"
        }
        return "Not Rated"
    }
    fun labelizeRating(rating: Double): String {
        if (0.0 < rating && rating <= 1.0) {
            return "Terrible"
        } else if (1.0 < rating && rating <= 2.0) {
            return "Bad"
        } else if (2.0 < rating && rating <= 3.0) {
            return "Okay"
        } else if (3.0 < rating && rating <= 4.0) {
            return "Good"
        } else if (4.0 < rating && rating <= 5.0) {
            return "Excellent"
        }
        return "Not Rated"
    }
}
