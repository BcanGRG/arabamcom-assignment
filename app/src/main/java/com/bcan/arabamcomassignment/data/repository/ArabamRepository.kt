package com.bcan.arabamcomassignment.data.repository

import com.bcan.arabamcomassignment.data.model.CarListQueries
import com.bcan.arabamcomassignment.data.model.response.CarDetailResponse
import com.bcan.arabamcomassignment.data.model.response.CarListResponse
import com.bcan.arabamcomassignment.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ArabamRepository {

    suspend fun getCarList(query: CarListQueries): Flow<NetworkResult<List<CarListResponse>>>

    suspend fun getCarDetail(id: Int): Flow<NetworkResult<CarDetailResponse>>
}