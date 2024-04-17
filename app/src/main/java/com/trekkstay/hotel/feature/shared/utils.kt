package com.trekkstay.hotel.feature.shared

object Utils {
    fun formatPrice(price: Double): String {
        val formattedPrice = String.format("%.2f", price)
        return if (formattedPrice.endsWith(".00")) {
            formattedPrice.substring(0, formattedPrice.length - 3)
        } else if (formattedPrice.endsWith("0")) {
            formattedPrice.substring(0, formattedPrice.length - 1)
        } else {
            formattedPrice
        }
    }
}