package com.android.espermobiles.api

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

//retrofit to make network connections
interface MobileApi {

    @GET("/mhrpatel12/esper-assignment/db")
    fun getAllDetails(): Observable<Response<ResponseApiModel>>
}