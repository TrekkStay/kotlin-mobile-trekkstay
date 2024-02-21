package com.trekkstay.hotel.env

import java.io.FileInputStream
import java.util.Properties

object Env {
    private val properties = loadProperties()

    val apiKey: String get() = properties.getProperty("API_KEY", "")
    val port: String get() = properties.getProperty("PORT", "")

    private fun loadProperties(): Properties {
        val properties = Properties()
        val file = FileInputStream("local.properties")
        properties.load(file)
        return properties
    }
}
