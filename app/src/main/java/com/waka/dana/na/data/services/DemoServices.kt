package com.waka.dana.na.data.services

import com.waka.dana.na.domain.model.DemoParam
import com.waka.dana.na.domain.model.DemoResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by hvngoc on 7/29/22
 */
interface DemoServices {

    @GET("demo")
    fun getListTypes(): Call<DemoResult>

    @POST("demo")
    fun addNewType(@Body param: DemoParam): Call<DemoResult>
}