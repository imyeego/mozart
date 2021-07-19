package com.imyeego.mozart

import okhttp3.Request
class RequestWrapper {
    var url: String? = null
    var method: String? = null
    var body: String? = null

    var param: Map<String, String>? = null

    var header: Map<String, String>? = null

    var headers: List<Map<String, String>>? = null

    internal var success: (String) -> Unit = {}
    internal var failure: (Throwable) -> Unit = {}
    fun onSuccess(onSucc: (String) -> Unit) {
        success = onSucc
    }

    fun onFailure(onFail: (Throwable) -> Unit) {
        failure = onFail
    }

}

fun http(init: RequestWrapper.() -> Unit) {
    val request = RequestWrapper()
    request.init()
    exec(request)
}

fun exec(request: RequestWrapper) {
    val realReq: Request = Request.Builder().build()



}

