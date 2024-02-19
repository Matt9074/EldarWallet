package com.example.eldarfinal.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import kotlinx.coroutines.GlobalScope
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class QRCodeGenerator(private val client: OkHttpClient) {

    fun generateQRCode(data: String, userId: String, apiKey: String): Bitmap? {
        val mediaType = "application/x-www-form-urlencoded".toMediaType()

        val requestBody = """
            content=${data.urlEncode()}&width=256&height=256&fg-color=%23000000&bg-color=%23ffffff
        """.trimIndent().toRequestBody(mediaType)

        val request = Request.Builder()
            .url("https://neutrinoapi.net/qr-code")
            .post(requestBody)
            .addHeader("User-ID", userId)
            .addHeader("API-Key", apiKey)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.byteStream()
                val qrCodeBitmap = BitmapFactory.decodeStream(responseBody)
                responseBody?.close()
                return qrCodeBitmap
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    private fun String.urlEncode(): String {
        return java.net.URLEncoder.encode(this, "UTF-8")
    }
}









