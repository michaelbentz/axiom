package com.michaelbentz.axiom.util

import android.util.Log
import com.michaelbentz.axiom.TAG
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseBodyCallback(
    private val onSuccessfulResponse: (responseBody: ResponseBody) -> Unit
) : Callback<ResponseBody> {

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                onSuccessfulResponse(responseBody)
            }
        }
    }

    override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
        Log.e(TAG, "onFailure($call, ${throwable.message})", throwable)
    }
}