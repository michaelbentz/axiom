package com.michaelbentz.axiom.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QrCodeService {

    @GET("create-qr-code/?size=750x750")
    fun createQrCode(@Query("data") data: String): Call<ResponseBody>
}