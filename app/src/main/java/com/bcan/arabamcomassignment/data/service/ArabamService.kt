package com.bcan.arabamcomassignment.data.service

import com.bcan.arabamcomassignment.data.model.response.CarDetailResponse
import com.bcan.arabamcomassignment.data.model.response.CarListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArabamService {

    @GET("listing")
    suspend fun getCarList(
        @Query("sort") sort: Int? = 1,
        @Query("sortDirection") sortDirection: Int? = 0,
        @Query("skip") skip: Int? = 0,
        @Query("take") take: Int = 20
    ): Response<List<CarListResponse>>

    @GET("detail")
    suspend fun getCarDetail(@Query("id") id: Int): Response<CarDetailResponse>

}