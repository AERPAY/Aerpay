package com.client.aerpaymerchant.network.listeners

import org.json.JSONObject


interface RetrofitResponseListener {
    fun onPreExecute()

    fun onSuccess(statusCode: Int, jsonObject: JSONObject, response: String)

    fun onError(statusCode: Int, message: String = "", jsonObject: JSONObject? = null)
}