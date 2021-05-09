package com.android.espermobiles.api

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface MobileApi {

    @GET
    fun getAllDetails(
        @Url url : String
    ): Observable<Response<ResponseApiModel>>
}