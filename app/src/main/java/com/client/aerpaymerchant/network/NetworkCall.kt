package com.client.aerpaymerchant.network

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.client.aerpaymerchant.R
import com.client.aerpaymerchant.network.listeners.DefaultActionPerformer
import com.client.aerpaymerchant.network.listeners.NoInternetListener
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit


class NetworkCall(context: Context) : Callback<ResponseBody> {

    private var requestType = REQUEST_TYPE_POST
    private var endPoint = ""
    private var mContext: Context? = null


    private var shouldPromptOnNoInternet = true
    private var noInternetPromptType = NO_INTERNET_PROMPT_TOAST
    private var snackbarView: View? = null
    private var noInternetListener: NoInternetListener? = null

    private var retrofitResponseListener: RetrofitResponseListener? = null

    private var requestObject: Any? = null
    private var requestParams: HashMap<String, String>? = HashMap()
    private var requestFiles: HashMap<String, File>? = null
    private var isMultipleFiles = false
    private var headers = HashMap<String, String>()

    private var call: Call<ResponseBody>? = null

    private val printBuilder = StringBuilder("\n API EndPoint : ")

    private var isMultipartCall = false

    /* Internet Handling*/
    private val isConnectedToInternet: Boolean
        get() {
            val connectivity =
                mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivity.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isAvailable && activeNetwork.isConnected
        }

    init {
        this.mContext = context
    }

    fun setEndPoint(endPoint: String): NetworkCall {
        this.endPoint = endPoint
        return this
    }

    fun setNoInternetPromptType(noInternetPromptType: Int): NetworkCall {
        this.noInternetPromptType = noInternetPromptType
        return this
    }

    fun setNoInternetPromptType(noInternetPromptType: Int, snackBarView: View): NetworkCall {
        this.noInternetPromptType = noInternetPromptType
        this.snackbarView = snackBarView
        return this
    }

    fun shouldPromptOnNoInternet(shouldPromptOnNoInternet: Boolean): NetworkCall {
        this.shouldPromptOnNoInternet = shouldPromptOnNoInternet
        return this
    }

    fun setNoInternetListener(noInternetListener: NoInternetListener): NetworkCall {
        this.noInternetListener = noInternetListener
        return this
    }

    fun setResponseListener(retrofitRxResponseListener: RetrofitResponseListener): NetworkCall {
        this.retrofitResponseListener = retrofitRxResponseListener
        return this
    }


    fun setRequestObject(requestObject: Any): NetworkCall {
        this.requestObject = requestObject
        return this
    }

    fun setRequestParams(params: HashMap<String, String>): NetworkCall {
        this.requestParams = params
        return this
    }

    fun setHeaders(headers: HashMap<String, String>): NetworkCall {
        this.headers = headers
        return this
    }

    fun setBaseURl(baseUrl: String): NetworkCall {
        Companion.baseUrl = baseUrl
        return this
    }

    fun setHeader(token: String?): NetworkCall {
        if (token == null) {
            headers[APIConstants.AUTHORIZATION] = ""
        } else
            headers[APIConstants.AUTHORIZATION] = token
        for ((key, value) in headers) {
            Log.e("header", key + "\t" + value)
        }
        return this
    }

    fun setFiles(fileParams: HashMap<String, File>): NetworkCall {
        this.requestFiles = fileParams
        this.isMultipartCall = true
        return this
    }

    fun setMultipartCall(multipartCall: Boolean) {
        isMultipartCall = multipartCall
    }

    fun setMultipleFiles(multiple: Boolean): NetworkCall {
        isMultipleFiles = multiple
        return this
    }

    private fun showNoInternetAlert() {

        val builder = AlertDialog.Builder(mContext!!)
        builder.setTitle(mContext!!.resources.getString(R.string.app_name))
        builder.setCancelable(true)

        builder.setMessage(mContext!!.getString(R.string.no_internet))
        builder.setPositiveButton(mContext!!.getString(android.R.string.ok)) { dialog, _ -> dialog.cancel() }
        builder.create().show()
    }

    fun makeEmptyRequestCall() {
        requestParams = HashMap()
        makeCall()
    }

    fun makeCall(): NetworkCall {

        if (requestObject == null && requestParams == null) {
            Log.e("tag","No Request Source is Provided");
        } else {
            if (isConnectedToInternet) {

                Log.e("API EndPoint" , endPoint );
                printBuilder.append(endPoint).append("\n\n").append("Headers\n")

                actionPerformer?.onActionPerform(headers, requestParams)

                if (headers.size > 0) {
                    for ((key, value) in headers) {
                        printBuilder.append(key).append("=>").append(value).append("\n")
                    }
                } else {
                    printBuilder.append("Headers are Empty")
                }

                if (retrofitResponseListener != null) {
                    retrofitResponseListener!!.onPreExecute()
                }


                if (requestObject != null) {
                    makeRequestWithObject(requestObject!!)
                } else {
                    makeRequestWithParams(this.requestParams!!)
                }


            } else {
                if (shouldPromptOnNoInternet) {
                    when (noInternetPromptType) {
                        NO_INTERNET_PROMPT_TOAST -> Toast.makeText(
                            mContext,
                            mContext!!.getText(R.string.no_internet),
                            Toast.LENGTH_SHORT
                        ).show()
                        NO_INTERNET_PROMPT_ALERT -> showNoInternetAlert()
                    }
                }
                if (noInternetListener != null) {
                    noInternetListener!!.onNoInternet()
                }
            }
        }

        return this
    }

    /**
     * cancel ongoing request if any
     */
    fun cancelRequest() {
        if (!call!!.isCanceled)
            call!!.cancel()
    }

    private fun makeRequestWithObject(requestClass: Any) {

        printBuilder.append("\n\n").append("Request Object\n")
        printBuilder.append(requestClass.toString()).append("\n\n")

        call = instance.APICall(endPoint, headers, requestClass)
        call!!.enqueue(this)
    }

    private fun makeRequestWithParams(requestParams: HashMap<String, String>) {

        printBuilder.append("\n\n").append("Request Params\n")

        if (isMultipartCall) {
            val bodyParams = HashMap<String, RequestBody>()
            val bodyList = arrayListOf<MultipartBody.Part>()

            if (requestParams.size > 0) {
                for ((key, value) in requestParams) {
                    printBuilder.append(key).append("=>").append(value).append("\n")
                    bodyParams[key] = createPartFromString(value)
                }
            } else {
                printBuilder.append(" Params are empty ")
            }


            if (requestFiles != null && requestFiles!!.size > 0) {
                printBuilder.append("\n\n").append("Files to Upload\n")

                for ((key, value) in requestFiles!!) {
                    printBuilder.append(key).append("=>").append(value.path).append("\n")
                    val fileName = key + "\"; filename=\"" + value.name
                    val fileBody = createPartFromFile(value)
                    if (isMultipleFiles) {
                        bodyList.add(
                            MultipartBody.Part.createFormData(
                                "image",
                                value.name,
                                fileBody
                            )
                        )
                    } else {
                        bodyParams[fileName] = fileBody
                    }
                }

            }
            call = if (isMultipleFiles) {
                instance.APIMultipartCall(endPoint, headers, bodyParams, bodyList)
            } else {
                instance.APIMultipartCall(endPoint, headers, bodyParams)
            }
            call!!.enqueue(this)
        } else {
            if (requestParams.size > 0) {
                for ((key, value) in requestParams) {
                    printBuilder.append(key).append("=>").append(value).append("\n")
                }
            } else {
                printBuilder.append(" Params are empty ")
            }
            call = when (requestType) {
                REQUEST_TYPE_GET -> instance.APICall(endPoint, headers)
                else -> instance.APICall(endPoint, headers, requestParams)
            }
            call!!.enqueue(this)
        }
    }

    private fun createPartFromFile(file: File): RequestBody {
        return file.asRequestBody(getMimeType(file.path).toMediaTypeOrNull())
    }

    private fun createPartFromString(value: String): RequestBody {

        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        handleResponse(response)
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        handleError(t)

        printBuilder.append("\n\nResponse\n")
        printBuilder.append("Call to the API Failed")
        printBuilder.append("\n\nThank you\n\n")
        copyToClipBoard()
    }

    private fun handleResponse(response: Response<ResponseBody>) {

        printBuilder.append("\n\nStatusCode : ").append(response.code().toString()).append("")

        try {
            if (response.body() != null) {
                val body = response.body()!!.string()
                printBuilder.append("\n\nResponse\n")
                printBuilder.append(body)
                printBuilder.append("\n\n Thank you\n\n")
                copyToClipBoard()
                val jsonObject = JSONObject(body.toString())

                var status = false

                if (!jsonObject.isNull("msg") && jsonObject.getInt("code") == 200) {
                    status = true
                }

                var dataJsonObject = JSONObject()

                if (!jsonObject.isNull("msg")) {
                    try {
                        dataJsonObject = jsonObject.optJSONObject("msg")!!
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                if (status) {
                    if (retrofitResponseListener != null) {
                        retrofitResponseListener!!.onSuccess(
                            response.code(),
                            dataJsonObject,
                            body
                        )
                        Log.e("tag","Success Response : " + jsonObject.optJSONObject("msg"))
                    }
                } else {
                    val errorMessage = getMessageFromResponseObject(mContext!!, jsonObject)
                    if (retrofitResponseListener != null) {
                        retrofitResponseListener!!.onError(
                            response.code(),
                            errorMessage,
                            jsonObject
                        )
                        Log.e("tag","Error Response : $body");
                    }
                }
            } else {
                val body = response.errorBody()!!.string()
                Log.e("tag","Error Body =>$body");

                printBuilder.append("\n\nResponse\n")
                printBuilder.append(body)
                printBuilder.append("\n\nThank you\n\n")

                copyToClipBoard()

                var message = mContext!!.getString(R.string.something_went_wrong)

                try {
                    val obj = JSONObject(body)
                    message = getMessageFromResponseObject(mContext!!, obj)
                } catch (e: JSONException) {
                    e.printStackTrace()
                } finally {
                    if (retrofitResponseListener != null) {
                        retrofitResponseListener!!.onError(response.code(), message)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            if (retrofitResponseListener != null) {
                retrofitResponseListener!!.onError(
                    response.code(),
                    mContext!!.getString(R.string.something_went_wrong)
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            if (retrofitResponseListener != null) {
                retrofitResponseListener!!.onError(
                    response.code(),
                    mContext!!.getString(R.string.something_went_wrong)
                )
            }
        }
    }

    /**
     *  @param obj jsonObject to extract error messages from
     */
    private fun getMessageFromResponseObject(context: Context, obj: JSONObject): String {
        var errorMsg = mContext!!.getString(R.string.something_went_wrong)

        if (!obj.isNull("message")) {
            errorMsg = obj.optString("message")
        }

        if (!obj.isNull("errorMessage")) {
            errorMsg = obj.optString("errorMessage")
        }

        return errorMsg
    }

    private fun handleError(e: Throwable) {
        e.printStackTrace()

        val errorMessage: String =
            if (e.message != null && e.message!!.isNotEmpty()) {
                e.message!!
            } else {
                "Exception"
            }

        if (retrofitResponseListener != null) {
            retrofitResponseListener!!.onError(500, errorMessage)
        }
    }

    private fun copyToClipBoard() {
        if (isDebuggable) {
            val clipboard =
                mContext!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("API Data", printBuilder.toString())
            clipboard.setPrimaryClip(clip)
        }
    }

    fun setRequestType(requestType: Int): NetworkCall {
        this.requestType = requestType
        return this
    }

    fun setActionPerformer(actionPerformer: DefaultActionPerformer) {
        Companion.actionPerformer = actionPerformer
    }

    companion object {
        private const val MULTIPART_FORM_DATA = "multipart/form-data"
        const val NO_INTERNET_PROMPT_TOAST = 0
        const val NO_INTERNET_PROMPT_ALERT = 2
        const val REQUEST_TYPE_GET = 1
        const val REQUEST_TYPE_POST = 0

        private var baseUrl: String = APIConstants.BASE_URL

        private var isDebuggable = true

        private var actionPerformer: DefaultActionPerformer? = null


        fun with(context: Context): NetworkCall {
            return NetworkCall(context)
        }

        fun setIsDebuggable(isDebuggable: Boolean) {
            Companion.isDebuggable = isDebuggable
        }

        // Retrofit API Interface Instance Handling
        private var apiInterface: ApiInterface? = null

        private val instance: ApiInterface
            get() {
                if (apiInterface == null) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    val client = OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor(interceptor).build()
                    val retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    apiInterface = retrofit.create(ApiInterface::class.java)
                }
                return apiInterface!!
            }
    }

    fun getMimeType(path: String): String {
        var type = "image/jpeg" // Default Value
        val extension = MimeTypeMap.getFileExtensionFromUrl(path);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension).toString()
        }
        return type
    }
}