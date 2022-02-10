package com.client.aerpaymerchant.network

import okhttp3.MultipartBody
import java.util.HashMap

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @POST
    fun APICall(@Url endPoint: String, @HeaderMap hashMap: HashMap<String, String>, @Body requestClass: Any): Call<ResponseBody>

    @FormUrlEncoded
    @POST
    fun APICall(@Url endPoint: String, @HeaderMap hashMap: HashMap<String, String>, @FieldMap fields: HashMap<String, String>): Call<ResponseBody>

    @Multipart
    @POST
    fun APIMultipartCall(@Url endPoint: String, @HeaderMap hashMap: HashMap<String, String>, @PartMap fields: HashMap<String, RequestBody>): Call<ResponseBody>

    @Multipart
    @POST
    fun APIMultipartCall(@Url endPoint: String, @HeaderMap hashMap: HashMap<String, String>, @PartMap fields: HashMap<String, RequestBody>, @Part  file :List<MultipartBody.Part>): Call<ResponseBody>

    @GET
    fun APICall(@Url endPoint: String, @HeaderMap hashMap: HashMap<String, String>): Call<ResponseBody>
}
